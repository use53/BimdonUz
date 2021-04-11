package ru.examples.bilimdonuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.examples.bilimdonuz.databinding.ActivityMainBinding
import ru.examples.bilimdonuz.ui.vpactity.vpfragment.ViewPagerFragment
import ru.examples.bilimdonuz.ui.vpquestion.VpQuestionViewModel

class MainActivity : AppCompatActivity(),ViewPagerFragment.IOnAnswersCheckedListener{
    private lateinit var binding:ActivityMainBinding


    private val vpQuestionViewModel:VpQuestionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onAnswersChecked(isCorrect: Boolean) {
        vpQuestionViewModel.corrent(isCorrect)
    }



}