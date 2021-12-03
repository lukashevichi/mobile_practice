package by.maxluxs.practicallesson2.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.maxluxs.practicallesson2.R
import by.maxluxs.practicallesson2.model.Student
import java.lang.Exception

class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    lateinit var titleTV: TextView
    lateinit var descriptionTV: TextView
    lateinit var surnameET: EditText
    lateinit var nameET: EditText
    lateinit var patronymicET: EditText
    lateinit var groupNumberET: EditText
    lateinit var facultyET: EditText
    lateinit var addB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (pageViewModel.index.value) {
            1 -> inflater.inflate(R.layout.fragment_variant1, container, false)
            2 -> inflater.inflate(R.layout.fragment_variant2, container, false)
            else -> inflater.inflate(R.layout.fragment_variant3, container, false)
        }
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private fun initViews() {
        titleTV = requireView().findViewById(R.id.section_title)
        titleTV.text = "Student card number ${pageViewModel.index.value}"
        descriptionTV = requireView().findViewById(R.id.section_description)
        descriptionTV.text =
            "Enter all the fields on the student number ${pageViewModel.index.value}" +
                    " card and click add new student."
        surnameET = requireView().findViewById(R.id.surname_et)
        nameET = requireView().findViewById(R.id.name_et)
        patronymicET = requireView().findViewById(R.id.patronymic_et)
        groupNumberET = requireView().findViewById(R.id.group_number_et)
        facultyET = requireView().findViewById(R.id.faculty_et)
        addB = requireView().findViewById(R.id.add_b)
    }

    private fun listenAdd() {
        addB.setOnClickListener {
            try {
                pageViewModel.setStudent(
                    Student(
                        surname = surnameET.text.toString(),
                        name = nameET.text.toString(),
                        patronymic = patronymicET.text.toString(),
                        groupNumberET.text.toString(),
                        faculty = facultyET.text.toString()
                    )
                )
                surnameET.setText("")
                nameET.setText("")
                patronymicET.setText("")
                groupNumberET.setText("")
                facultyET.setText("")
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Something went wrong",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNewStudent()
        initViews()
        listenAdd()
    }

    private fun observeNewStudent() {
        pageViewModel.student.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                "Added new student: \n$it",
                Toast.LENGTH_SHORT
            ).apply {
                setGravity(Gravity.BOTTOM, 200, 200)
                show()
            }

        }
    }
}