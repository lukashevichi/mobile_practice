package by.maxluxs.pr5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.maxluxs.pr5.databinding.ItemImageBinding
import by.maxluxs.pr5.model.ImageModel

class ImagesAdapter : ListAdapter<ImageModel, ImageHolder>(COMPARATOR), ImageAdapterCallback {

    var openImageCallback: ((image: ImageModel) -> Unit)? = null

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<ImageModel>() {

            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel) =
                oldItem.bitmap.sameAs(newItem.bitmap)

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel) =
                oldItem.hashCode() == newItem.hashCode()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ImageHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, this) }
    }

    override fun openImage(image: ImageModel) {
        openImageCallback?.invoke(image)
    }

}