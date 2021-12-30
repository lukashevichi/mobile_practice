package by.maxluxs.pr5

import android.content.ContentResolver
import android.content.ContentUris
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentResolver: ContentResolver
) : ViewModel() {

    private var imagesLiveData: MutableLiveData<List<Bitmap>> = MutableLiveData()
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
                        Log.e("!!!", it.toString())
                        MediaStore.Images.Media.getBitmap(contentResolver, it)
                    }
            }
        }
    }

    private fun getImage(selectedPhotoUri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT < 28) MediaStore.Images.Media.getBitmap(
            this.contentResolver,
            selectedPhotoUri
        )
        else {
            val source = ImageDecoder.createSource(this.contentResolver, selectedPhotoUri)
            ImageDecoder.decodeBitmap(source)
        }
    }
}