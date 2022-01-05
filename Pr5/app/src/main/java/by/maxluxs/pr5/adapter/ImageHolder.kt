package by.maxluxs.pr5.adapter

import androidx.recyclerview.widget.RecyclerView
import by.maxluxs.pr5.model.ImageModel
import by.maxluxs.pr5.databinding.ItemImageBinding

class ImageHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bitmap: ImageModel, callback: ImageAdapterCallback) {
        binding.imageView.setImageBitmap(bitmap.bitmap)
        binding.textViewName.text = bitmap.title
        binding.layout.setOnClickListener {
            callback.openImage(bitmap)
        }
    }

}