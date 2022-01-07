package by.maxluxs.pr7

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(intent)

        findViewById<Button>(R.id.battaryB).setOnClickListener {
            startActivity(Intent(this, BatteryActivity::class.java))
        }
        findViewById<Button>(R.id.contactsB).setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
        findViewById<Button>(R.id.wifiB).setOnClickListener {
            startActivity(Intent(this, WifiActivity::class.java))
        }

    }
}