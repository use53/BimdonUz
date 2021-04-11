package ru.examples.bilimdonuz.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.examples.bilimdonuz.model.AnswerModel
import ru.examples.bilimdonuz.ui.vpactity.vpfragment.ViewPagerFragment


class TestListAdapter(activity: FragmentActivity,
                      val list:MutableList<AnswerModel>)
    :FragmentStateAdapter(activity){
    override fun getItemCount(): Int =list.size

    override fun createFragment(position: Int): Fragment =
        ViewPagerFragment.newInstance(list[position])

}
