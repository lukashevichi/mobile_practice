package by.maxluxs.kr1.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.maxluxs.kr1.model.vo.HomeFlower

class FlowersAdapter : ListAdapter<HomeFlower, FlowerItemHolder>(COMPARATOR), FlowersCallback {

    var openFlowerCallback: ((HomeFlower) -> Unit)? = null
    var deleteFlowerCallback: ((HomeFlower) -> Unit)? = null

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<HomeFlower>() {

            override fun areContentsTheSame(oldItem: HomeFlower, newItem: HomeFlower): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: HomeFlower, newItem: HomeFlower): Boolean {
                return oldItem.uuid == newItem.uuid
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerItemHolder =
        FlowerItemHolder.create(parent)

    override fun onBindViewHolder(holder: FlowerItemHolder, position: Int) {
        getItem(position)?.let { flower ->
            holder.bind(flower, this)
        }
    }

    override fun openFlower(flower: HomeFlower) {
        openFlowerCallback?.invoke(flower)
    }

    override fun deleteFlower(flower: HomeFlower) {
        deleteFlowerCallback?.invoke(flower)
    }

}