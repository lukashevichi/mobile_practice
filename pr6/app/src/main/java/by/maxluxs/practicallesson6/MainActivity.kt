package by.maxluxs.practicallesson6

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import by.maxluxs.practicallesson6.databinding.ActivityMainBinding
import java.util.*
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val fileOperations = FileOperations(this)

    private val studentsRepository = StudentsRepository(fileOperations)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenWrite()
        listenRead()
    }

    private fun listenWrite() {
        binding.buttonWrite.setOnClickListener {
            if (binding.welcomeLayout.isVisible) {
                binding.welcomeLayout.isVisible = false
            }
            if (binding.studentCard.isVisible) {
                writeStudents()
            } else {
                binding.studentCard.isVisible = true
                binding.infoCard.isVisible = false
            }
        }
    }

    private fun listenRead() {
        binding.buttonRead.setOnClickListener {
            if (binding.welcomeLayout.isVisible) {
                binding.welcomeLayout.isVisible = false
            }
            if (binding.infoCard.isVisible) {
                readStudents()
            } else {
                binding.studentCard.isVisible = false
                binding.infoCard.isVisible = true
            }
        }
    }

    private fun writeStudents() {
        val writeResponse = studentsRepository.writeStudent(
            StudentModel(
                uuid = UUID.randomUUID().hashCode().toString().drop(4),
                binding.nameTextWrite.text.toString(),
                binding.surnameTextWrite.text.toString(),
                binding.facultyWrite.text.toString()
            )

        )
        if (writeResponse) {
            Toast.makeText(
                this,
                "${binding.nameTextWrite.text} студент создан", Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this, "I/O error", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun readStudents() {
        val readFileName: String = binding.fileNameRead.text.toString()
        val students = studentsRepository.readStudentsByName(readFileName)
        if (students != null) {
            binding.studentInformationList.apply {
                adapter = ArrayAdapter(
                    this@MainActivity,
                    R.layout.simple_list_item_1, students
                )
            }
        } else {
            Toast.makeText(
                this, "File not Found", Toast.LENGTH_SHORT
            ).show()
        }
    }

}