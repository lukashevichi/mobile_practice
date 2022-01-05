package by.maxluxs.pr5.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.maxluxs.pr5.R
import by.maxluxs.pr5.adapter.ImageAdapterCallback
import by.maxluxs.pr5.adapter.ImagesAdapter
import by.maxluxs.pr5.databinding.FragmentImagesBinding
import by.maxluxs.pr5.model.ImageModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment(), ImageAdapterCallback {

    private lateinit var binding: FragmentImagesBinding

    private val adapter: ImagesAdapter? get() = (binding.imagesRecycler.adapter as? ImagesAdapter)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderRecycler()
        observeImages()
    }


    private fun observeImages() = viewModel.imageList.observe(this) {
        adapter?.submitList(it)
    }

    private fun renderRecycler() = binding.imagesRecycler.apply {
        layoutManager = GridLayoutManager(requireContext(), 2)
        setHasFixedSize(true)
        addItemDecoration(SpacesItemDecoration(28))
        adapter = ImagesAdapter().apply {
            openImageCallback = this@ImagesFragment::openImage
        }
    }

    class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space
            } else {
                outRect.top = 0
            }
        }

    }

    override fun openImage(image: ImageModel) {
            val args = ImageFragment.createArgs(image)
            findNavController().navigate(R.id.action_imagesFragment_to_imageFragment, args)
    }

}
