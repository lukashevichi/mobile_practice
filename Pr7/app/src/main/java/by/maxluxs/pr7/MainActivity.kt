package by.maxluxs.pr7

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actv = findViewById<AutoCompleteTextView>(R.id.actv)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            listOf("English", "Hebrew", "German", "Gereek")
        )

        actv.setAdapter(adapter)


        val intent = Intent(ACTION_VIEW, Uri.parse("http://www.androidbook.com"))

        startActivity(intent)
    }
}