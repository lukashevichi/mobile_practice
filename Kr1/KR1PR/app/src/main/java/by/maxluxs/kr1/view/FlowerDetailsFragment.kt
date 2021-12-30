package by.maxluxs.kr1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.maxluxs.kr1.R
import by.maxluxs.kr1.databinding.ActivityMainBinding.inflate
import by.maxluxs.kr1.databinding.FragmentFlowerDetailsBinding
import by.maxluxs.kr1.databinding.ItemFlowerBinding.inflate
import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.viewModel.HomeFlowerDetailsViewModel
import by.maxluxs.kr1.viewModel.MainViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class FlowerDetailsFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(uid: String): FlowerDetailsFragment {
            val args = Bundle().apply { "uid" to uid }
            val fragment = FlowerDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: HomeFlowerDetailsViewModel by viewModels()

    private var _binding: FragmentFlowerDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlowerDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlower()
        listenEditButton()
    }

    private fun listenEditButton() = binding.editButton.setOnClickListener {
        viewModel.flower.value?.let {
            viewModel.saveFlower(
                HomeFlower(
                    category = binding.category.text.toString(),
                    instructions = binding.instructions.text.toString(),
                    photo = it.photo,
                    name = binding.name.text.toString(),
                    uuid = it.uuid
                )
            )
        }
    }

    private fun observeFlower() = viewModel.flower.observe(viewLifecycleOwner) {
        it?.let { flower ->
            binding.category.text = flower.category
            binding.name.text = flower.name
            binding.instructions.text = flower.instructions
            (requireActivity() as AppCompatActivity).supportActionBar?.title = flower.name
            setImage(flower)
        } ?: run { showError() }
    }

    private fun setImage(flower: HomeFlower) = Glide
        .with(requireContext())
        .load(flower.photo)
        .into(binding.photo)

    private fun showError() =
        Toast.makeText(requireContext(), "Ошибка с данными", Toast.LENGTH_SHORT).show()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}