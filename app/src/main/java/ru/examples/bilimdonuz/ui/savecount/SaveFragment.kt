package ru.examples.bilimdonuz.ui.savecount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.databinding.SaveFragmentBinding
import ru.examples.bilimdonuz.ui.vpquestion.VpQuestionViewModel

class SaveFragment : Fragment(R.layout.save_fragment) {

    private var savebinding: SaveFragmentBinding? = null
    private val vpQuestionViewModel: VpQuestionViewModel by activityViewModels()
    private val navController by lazy { Navigation.findNavController(requireActivity(), R.id.fragment) }
    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SaveFragmentBinding.bind(view)
        savebinding = binding
        binding.btCansels.setOnClickListener {
            navController.navigate(R.id.books_navigation)
        }
        binding.btSave.setOnClickListener {
            vpQuestionViewModel.onFirebaseSave(count)
            navController.navigate(R.id.books_navigation)
        }

        vpQuestionViewModel.saveCount.observe(viewLifecycleOwner, Observer {
            count = it
            binding.pieChart.centerText = it.toString()
            binding.pieChart.setCenterTextSize(50F)

        })
        vpQuestionViewModel.ldChart.observe(viewLifecycleOwner,
                Observer {
            binding.pieChart.data = it
            binding.pieChart.invalidate()

        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        savebinding = null
    }
}