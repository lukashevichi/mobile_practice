package by.maxluxs.practicallesson4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val animateModel: MutableLiveData<AnimateModel> by lazy {
        MutableLiveData<AnimateModel>().also {
            defaultAnimateModel()
        }
    }

    fun getAnimateModel(): LiveData<AnimateModel> {
        return animateModel
    }

    private fun defaultAnimateModel() = AnimateModel()

}