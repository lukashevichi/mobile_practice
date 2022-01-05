package by.maxluxs.pr5.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel(
    val title: String,
    val bitmap: Bitmap
) : Parcelable