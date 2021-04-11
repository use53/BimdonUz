package ru.examples.bilimdonuz.ui.savecount

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.databinding.DialogViewBinding
import ru.examples.bilimdonuz.databinding.SaveFragmentBinding
import ru.examples.bilimdonuz.model.SaveModel
import ru.examples.bilimdonuz.ui.vpquestion.VpQuestionViewModel

class SaveFragment :Fragment(R.layout.save_fragment){

    private var savebinding:SaveFragmentBinding?=null
    private val vpQuestionViewModel:VpQuestionViewModel by activityViewModels()
    private val navController by lazy { Navigation.findNavController(requireActivity(),R.id.fragment) }
    private var count =0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=SaveFragmentBinding.bind(view)
        savebinding=binding
       binding.btCansels.setOnClickListener {
           navController.navigate(R.id.books_navigation)
       }
        binding.btSave.setOnClickListener {
            dialogItem()
        }

        vpQuestionViewModel.saveCount.observe(viewLifecycleOwner, Observer {
            count=it
            binding.pieChart.centerText=it.toString()
            binding.pieChart.setCenterTextSize(50F)

        })
        vpQuestionViewModel.ldChart.observe(viewLifecycleOwner, Observer {
            binding.pieChart.data=it
            binding.pieChart.invalidate()

        })


    }

    private fun dialogItem() {
        val view=DialogViewBinding.inflate(layoutInflater)
        AlertDialog.Builder(requireContext())
            .setView(view.root)
            .setPositiveButton(R.string.dialog_ok) { dialog, _ ->
                val text=view.gbCodeDialog.text
                val saveModel=SaveModel(text.toString(),count.toString())
                vpQuestionViewModel.onFirebaseSave(saveModel)
                navController.navigate(R.id.books_navigation)
                dialog.dismiss()
            }.create()
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        savebinding=null
    }
}