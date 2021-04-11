package ru.examples.bilimdonuz.ui.vpquestion

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer2
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.databinding.VpquestionFragmentBinding
import ru.examples.bilimdonuz.vp.TestListAdapter
import java.util.concurrent.TimeUnit

class VpQuestionFragment ():Fragment(R.layout.vpquestion_fragment){

    private  var vpbinding:VpquestionFragmentBinding?=null
    private val vpQuestionViewModel:VpQuestionViewModel by activityViewModels()
    private var booleanArray=BooleanArray(0)
    private lateinit var dateStart:CountDownTimer
    private lateinit var testListAdapters:TestListAdapter
    private val FORMAT = "%02d:%02d:%02d"
    private val navController by lazy { Navigation.findNavController(requireActivity(),R.id.fragment) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=VpquestionFragmentBinding.bind(view)
        vpbinding=binding
        val bookflip= BookFlipPageTransformer2()
        bookflip.setEnableScale(true)
        bookflip.setScaleAmountPercent(10f)
         dateStart=object :CountDownTimer(1800000,1000){
            override fun onFinish() {
                val chek=booleanArray.filter {it}.size
                vpQuestionViewModel.saveItem(chek)
                navController.navigate(R.id.save_navigation)
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                vpbinding!!.tvDate.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))

            }
        }.start()

        onBack()
        vpQuestionViewModel.ldQuestionVM.observe(viewLifecycleOwner, Observer {
            testListAdapters= TestListAdapter(requireActivity(),it)
            binding.vpPager.adapter=testListAdapters
            binding.vpPager.setPageTransformer(bookflip)
          ///
            ///

            booleanArray= BooleanArray(it.size)

        })
          onItemBoolean()
        onClickButton()
    }

    private fun onBack() {
        requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                     //requireActivity().finish()
                          navController.popBackStack(R.id.books_navigation,false)
                    }

                })
    }

    private fun onClickButton() {
        vpbinding!!.vpButton.setOnClickListener {
            dateStart.onFinish()
             dateStart.cancel()
        }
    }

    private fun onItemBoolean() {
        vpQuestionViewModel.isCorrent.observe(viewLifecycleOwner, Observer {
            booleanArray[vpbinding!!.vpPager.currentItem]=it
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        vpbinding=null
        dateStart.cancel()
    }

}

