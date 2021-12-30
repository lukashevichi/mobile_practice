package by.maxluxs.pr5.adapter

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import by.maxluxs.pr5.databinding.ItemImageBinding

class ImageHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bitmap: Bitmap, callback: ImageAdapterCallback) {

        binding.imageView.setImageBitmap(bitmap)

        binding.layout.setOnClickListener {
            callback.openImage(bitmap)
        }

    }
}