package ru.examples.bilimdonuz.ui.vpactity.vpfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.databinding.FragmentViewpagerBinding
import ru.examples.bilimdonuz.model.AnswerModel
import java.lang.RuntimeException


private const val ARG_PARAM_KEY = "ARG_PARAM_KEY"

class ViewPagerFragment : Fragment(R.layout.fragment_viewpager) {
    private var fragment:FragmentViewpagerBinding?=null
    private var answerModel: AnswerModel? = null
    private var listener:IOnAnswersCheckedListener?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IOnAnswersCheckedListener){
            listener=context
        }else{
            throw RuntimeException(context.toString()+"must on Back")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            answerModel = it.getParcelable(ARG_PARAM_KEY)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentViewpagerBinding.bind(view)
        fragment=binding
        answerItem()
    }

    private fun answerItem() {
        answerModel?.apply {
            fragment!!.tvQuesiton.text=this.answer.toString()
            fragment!!.answerA.text=this.answerA.toString()
            fragment!!.answerB.text=this.answerB.toString()
            fragment!!.answerC.text=this.answerC.toString()
        }
        fragment!!.rdGrup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.answer_a->{
                    isCorent(answerModel!!.answerA.toString())
                    }
                R.id.answer_c->{
                    isCorent(answerModel!!.answerC.toString())
                  }
                R.id.answer_b->{
                    isCorent(answerModel!!.answerB.toString())
                   }
            }
        }
    }

    private fun isCorent(answers: String) {
        if (answers==answerModel!!.title.toString()){
            listener!!.onAnswersChecked(true)
        }else{
            listener!!.onAnswersChecked(false)
        }
    }

    interface IOnAnswersCheckedListener{
        fun onAnswersChecked(isCorrect:Boolean)
    }


    companion object {
        fun newInstance(answerModel: AnswerModel) =
            ViewPagerFragment().apply {
                val bundle=Bundle()
                bundle.putParcelable(ARG_PARAM_KEY,answerModel)
                arguments=bundle
            }
    }
}