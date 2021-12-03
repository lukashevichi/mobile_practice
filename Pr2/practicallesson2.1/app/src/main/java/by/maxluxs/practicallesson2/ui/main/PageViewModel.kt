package by.maxluxs.practicallesson2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.maxluxs.practicallesson2.model.Student

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int>
        get() = _index

    private val _student = MutableLiveData<Student>()
    val student: LiveData<Student>
        get() = _student

    fun setStudent(student: Student) {
        _student.value = student
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

}