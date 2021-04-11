package ru.examples.bilimdonuz.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class NearbyListAdapter (fragmentActivity: FragmentActivity,
private val fragment:MutableList<Fragment>):FragmentStateAdapter( fragmentActivity){
    override fun getItemCount()=fragment.size

    override fun createFragment(position: Int): Fragment =fragment[position]

}