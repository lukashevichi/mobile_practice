package by.maxluxs.practicallesson2

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_task -> {
                val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
                alertDialog.apply {
                    setTitle(getString(R.string.task_title))
                    setMessage(getString(R.string.task_text))
                    setCancelable(true)
                    show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}