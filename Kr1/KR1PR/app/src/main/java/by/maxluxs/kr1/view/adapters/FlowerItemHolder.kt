package by.maxluxs.kr1.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import by.maxluxs.kr1.databinding.ItemFlowerBinding
import by.maxluxs.kr1.model.vo.HomeFlower
import com.bumptech.glide.Glide

class FlowerItemHolder(private val binding: ItemFlowerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): FlowerItemHolder =
            FlowerItemHolder(ItemFlowerBinding.inflate(LayoutInflater.from(parent.context)))
    }

    fun bind(item: HomeFlower, callback: FlowersCallback) {
        binding.name.text = item.name
        binding.category.text = item.category
        binding.photoCard.setOnClickListener {
            callback.openFlower(item)
        }
        binding.photoCard.setOnLongClickListener {
            callback.deleteFlower(item)
            true
        }
        setImage(item)
    }

    private fun setImage(flower: HomeFlower) = Glide
        .with(binding.root.context)
        .load(flower.photo)
        .into(binding.photo)

}