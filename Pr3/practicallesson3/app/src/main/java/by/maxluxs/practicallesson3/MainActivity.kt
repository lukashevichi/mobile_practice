package by.maxluxs.practicallesson3

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import by.maxluxs.practicallesson3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val TITLE = "Developer info"

        const val DEVELOPER_INFO = "Developer: " +
                "\n    Lukashevich Maxim Alexandrovich\n" +
                "Studies:" +
                "\n    4th year student ASOI BSUIR\n" +
                "Works:" +
                "\n    an Android developer at 54origins"

        const val TASK = "Добавьте еще одну кнопку для установки желтого цвета.\n" +
                "Добавьте кнопку для выдачи диалогового сообщения о разработчике\n" +
                "программы.\n" +
                "Проверьте различные способы размещения кнопок на сценарии и задания их\n" +
                "параметров."

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reset -> {
                binding.mainLayout.setBackgroundResource(R.color.white)
                true
            }
            R.id.task -> {
                showDialog("Task", TASK)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.blueButton.setOnClickListener {
            binding.mainLayout.setBackgroundResource(R.color.blue)
        }

        binding.greenButton.setOnClickListener {
            binding.mainLayout.setBackgroundResource(R.color.green)
        }

        binding.redButton.setOnClickListener {
            binding.mainLayout.setBackgroundResource(R.color.red)
        }

        binding.yellowButton.setOnClickListener {
            binding.mainLayout.setBackgroundResource(R.color.yellow)
        }

        binding.developerInfoButton.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog(title: String = TITLE, message: String = DEVELOPER_INFO) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(
                "Ok"
            ) { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        }
        dialog
            .show()
    }
}