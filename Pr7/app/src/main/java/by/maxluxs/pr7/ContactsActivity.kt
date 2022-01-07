package by.maxluxs.pr7

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.provider.ContactsContract

import android.content.ContentResolver
import android.database.Cursor
import android.widget.TextView


class ContactsActivity : AppCompatActivity() {

    private val REQUEST_ID_CONTACT = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        if (checkAndRequestPermissions()) {
            findViewById<TextView>(R.id.contactsText).text = getContactList().toString()
        }

    }

    private fun checkAndRequestPermissions(): Boolean {
        val read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        val write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS)
        }
        if (write != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CONTACTS)
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_CONTACT
            )
            return false
        }
        return true
    }

    data class Contact(
        val name: String,
        val number: String
    )

    private fun getContactList(): List<Contact> {
        val contactsNumbers = mutableListOf<Contact>()
        val cr = contentResolver
        val cur: Cursor? = cr.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )
        if ((cur?.count ?: 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                val id: String = cur.getString(
                    cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
                )
                val name: String = cur.getString(
                    cur.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)
                )
                if (cur.getInt(
                        cur.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                    ) > 0
                ) {
                    val pCur: Cursor? = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    while (pCur?.moveToNext() == true) {
                        val phoneNo: String = pCur.getString(
                            pCur.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )
                        contactsNumbers.add(Contact(name = name, number = phoneNo))
                    }
                    pCur?.close()
                }
            }
        }
        cur?.close()
        return contactsNumbers
    }

}