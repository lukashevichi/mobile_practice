package by.maxluxs.pr5.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.maxluxs.pr5.databinding.ItemImageBinding

class ImagesAdapter : ListAdapter<Bitmap, ImageHolder>(COMPARATOR), ImageAdapterCallback {

    val openImageCallback: ((Bitmap) -> Unit)? = null

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Bitmap>() {

            override fun areItemsTheSame(oldItem: Bitmap, newItem: Bitmap) =
                oldItem.sameAs(newItem)

            override fun areContentsTheSame(oldItem: Bitmap, newItem: Bitmap) =
                oldItem.hashCode() == newItem.hashCode()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ImageHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(
                it,
                this
            )
        }

    }

    override fun openImage(bitmap: Bitmap) {
        openImageCallback?.invoke(bitmap)
    }

}