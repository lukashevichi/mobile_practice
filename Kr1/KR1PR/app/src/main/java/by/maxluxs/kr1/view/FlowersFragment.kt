package by.maxluxs.kr1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.maxluxs.kr1.R
import by.maxluxs.kr1.databinding.FragmentFlowersBinding
import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.view.adapters.FlowersAdapter
import by.maxluxs.kr1.view.adapters.FlowersCallback
import by.maxluxs.kr1.viewModel.HomeFlowersViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Flowers list [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FlowersFragment : Fragment(), FlowersCallback {

    private var _binding: FragmentFlowersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeFlowersViewModel by viewModels()

    private val flowerAdapter get() = binding.flowersRecycler.adapter as? FlowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlowersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.flowersRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            setHasFixedSize(true)
            adapter = FlowersAdapter().apply {
                openFlowerCallback = this@FlowersFragment::openFlower
                deleteFlowerCallback = this@FlowersFragment::deleteFlower
            }
        }
        observeFlowers()
    }

    private fun observeFlowers() = viewModel.flower.observe(viewLifecycleOwner) {
        flowerAdapter?.submitList(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openFlower(flower: HomeFlower) {
        findNavController().navigate(
            R.id.action_FirstFragment_to_SecondFragment,
            bundleOf("uid" to flower.uuid)
        )
    }

    override fun deleteFlower(flower: HomeFlower) {
        viewModel.deleteFlower(flower)
    }
}