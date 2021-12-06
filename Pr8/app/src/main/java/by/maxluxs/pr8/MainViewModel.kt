package by.maxluxs.pr8

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _url: MutableLiveData<String> by lazy(::MutableLiveData)
    val url get() = _url

    fun setUrl(url: String) {
        _url.value = when {
            (url.contains("http://") || url.contains("https://") || url.contains("www.")) -> url
            url.isNotEmpty() -> "https://www.google.com/search?q=$url&oq=$url"
            else -> "https://www.google.com"
        }
    }

}