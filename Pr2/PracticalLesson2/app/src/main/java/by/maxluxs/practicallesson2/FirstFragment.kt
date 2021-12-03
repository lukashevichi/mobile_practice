package by.maxluxs.practicallesson2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.maxluxs.practicallesson2.databinding.FragmentFirstBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.fab.setOnClickListener { view ->
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Snackbar.make(view, "Fill out the form and click add", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initAdapter() {
        binding.studentRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = StudentAdapter(
                mutableListOf(
                    Student(
                        "Maksim",
                        "Lukashevich",
                        "Aleksandrovich",
                        "8906512",
                        "FINO"
                    )
                )
            )
        }
    }
}