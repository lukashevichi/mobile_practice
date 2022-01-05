package by.maxluxs.pr5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.maxluxs.pr5.databinding.FragmentImageBinding
import by.maxluxs.pr5.model.ImageModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ImageFragment : Fragment() {

    private var image: ImageModel? = null

    private lateinit var binding: FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image?.let { binding.image.setImageBitmap(it.bitmap) }
    }

    companion object {
        @JvmStatic
        fun newInstance(image: ImageModel) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, image)
                }
            }

        fun createArgs(image: ImageModel) = Bundle().apply {
            putParcelable(ARG_PARAM1, image)
        }
    }
}