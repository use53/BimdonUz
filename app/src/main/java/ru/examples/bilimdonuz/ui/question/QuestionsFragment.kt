package ru.examples.bilimdonuz.ui.question

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.adapters.BooksAdapters
import ru.examples.bilimdonuz.databinding.QuestionsFragmentBinding
import ru.examples.bilimdonuz.model.BooksModel
import ru.examples.bilimdonuz.onclick.IBooksOnClcik
import ru.examples.bilimdonuz.ui.vpquestion.NetworksStatus
import ru.examples.bilimdonuz.ui.vpquestion.VpQuestionViewModel

const val KEY_ID="KEY_ID"
class QuestionsFragment :Fragment(R.layout.questions_fragment), IBooksOnClcik {


    private var questionsFragmentBinding:QuestionsFragmentBinding?=null
    private val booksAdapter by lazy { BooksAdapters(this) }
    private val questionsViewModel:QuestionsViewModel by activityViewModels()
    private val navController by lazy { Navigation.findNavController(requireActivity(),R.id.fragment) }
    private val vpQuestionViewModel:VpQuestionViewModel by activityViewModels()

    private val obsevable=Observer<NetworksStatus>{
        when(it){
            is NetworksStatus.Success->successItem()
            is NetworksStatus.Loading->showLoading()
        }
    }

    private fun showLoading() {
        questionsFragmentBinding!!.lottieFile.visibility=View.VISIBLE
        questionsFragmentBinding!!.recBooks.visibility=View.INVISIBLE
    }

    private fun successItem() {
        questionsFragmentBinding!!.lottieFile.visibility=View.INVISIBLE
        navController.navigate(R.id.vpquestion_navigation)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=QuestionsFragmentBinding.bind(view)
        questionsFragmentBinding=binding
        questionAdapter()
    }

    private fun questionAdapter() {
        questionsFragmentBinding!!.recBooks.layoutManager=GridLayoutManager(requireContext(),2)
        questionsFragmentBinding!!.recBooks.adapter=booksAdapter
        questionsViewModel.BooksdbVM()
        questionsViewModel.ldBooksVM.observe(viewLifecycleOwner, Observer {
            questionsFragmentBinding!!.lottieFile.visibility=View.INVISIBLE
            booksAdapter.submitList(it)

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        questionsFragmentBinding=null
    }

    override fun onClickListener(booksModel: BooksModel) {
         vpQuestionViewModel.onQuestionVM(booksModel.name)
        vpQuestionViewModel.statusVm.observe(viewLifecycleOwner, obsevable)

    }
}