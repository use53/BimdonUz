package ru.examples.bilimdonuz.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.databinding.BooksFragmentBinding
import ru.examples.bilimdonuz.vp.NearbyListAdapter
import ru.examples.bilimdonuz.ui.news.NewsFragment
import ru.examples.bilimdonuz.ui.question.QuestionsFragment

class BooksFragment :Fragment(R.layout.books_fragment), TabLayoutMediator.TabConfigurationStrategy, Toolbar.OnMenuItemClickListener {

    private var booksFragmentBinding:BooksFragmentBinding?=null
    private val fragmentList by lazy {
       mutableListOf<Fragment>().apply {
           this.add(QuestionsFragment())
           this.add(NewsFragment())
       }

    }
    private val navController by lazy { Navigation.findNavController(requireActivity(),R.id.fragment) }

    private val nearbyListAdapter by lazy { NearbyListAdapter(requireActivity(),fragmentList) }
    private lateinit var titleItem:Array<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view, savedInstanceState)
        val binding=BooksFragmentBinding.bind(view)
        booksFragmentBinding=binding


        binding.appbarLayout.toolbarMain.inflateMenu(R.menu.menu)
        binding.appbarLayout.toolbarMain.setOnMenuItemClickListener(this)

        titleItem=resources.getStringArray(R.array.array_tab_item)
        binding.appbarLayout.vpLocation.adapter=nearbyListAdapter
        TabLayoutMediator(binding.appbarLayout.tabLayout,binding.appbarLayout.vpLocation,this)
            .attach()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        booksFragmentBinding=null
    }

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text=titleItem[position]
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item!!.itemId==R.id.menu_settings){
               navController.navigate(R.id.settings_fragment)
            return true
        }else if (item.itemId==R.id.menu_telegram){
            val telegram =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Developer_JE"))
            startActivity(telegram)
            return true
        }
        else
            return false
    }
}