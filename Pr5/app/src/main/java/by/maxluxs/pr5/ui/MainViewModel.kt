package by.maxluxs.pr5.ui

import android.R.attr
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toFile
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.maxluxs.pr5.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.R.attr.data
import java.io.File


@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentResolver: ContentResolver
) : ViewModel() {

    private var imagesLiveData: MutableLiveData<List<ImageModel>> = MutableLiveData()
    val imageList get() = imagesLiveData

    init {
        getAllImages()
    }

    private fun getLocalImagePaths(): MutableList<Uri> {
        val result = mutableListOf<Uri>()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID)
        contentResolver.query(uri, projection, null, null, null)?.use {
            while (it.moveToNext()) {
                result.add(
                    ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        it.getLong(0)
                    )
                )
            }
        }
        return result
    }

    private fun getAllImages() {
        viewModelScope.launch(Dispatchers.Main) {
            imagesLiveData.value = withContext(Dispatchers.IO) {
                getLocalImagePaths()
                    .map {
                        ImageModel(
                            title = getUriFileName(it),
                            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                        )
                    }
            }
        }
    }

    private fun getUriFileName(uri: Uri): String {
        return uri.path?.let {
            val file = File(it)
            val split = file.path.split(":")
            split.first()
        } ?: uri.lastPathSegment ?: ""
    }

}