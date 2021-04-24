package ru.examples.bilimdonuz.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.adapters.SaveAdapter
import ru.examples.bilimdonuz.common.invisible
import ru.examples.bilimdonuz.databinding.NewsFragmentBinding

class NewsFragment : Fragment(R.layout.news_fragment) {

    private var newfragment: NewsFragmentBinding? = null
    private val newsViewModel: NewsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = NewsFragmentBinding.bind(view)
        newfragment = binding
        val saveAdapter = SaveAdapter()
        binding.recItem.adapter = saveAdapter

        newsViewModel.firebaseRead()
        newsViewModel.ldRead.observe(viewLifecycleOwner, Observer {
            binding.lottieNews.invisible()
            saveAdapter.submitList(it)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        newfragment = null
    }
}
