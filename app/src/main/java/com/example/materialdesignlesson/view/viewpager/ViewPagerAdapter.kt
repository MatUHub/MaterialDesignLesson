package com.example.materialdesignlesson.view.viewpager

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.materialdesignlesson.view.main.PODFragment

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    private val fragments = arrayOf(PODFragment(0), PODFragment(1))
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Today"
            1 -> "Yesterday"
            else -> "null"
        }
    }
}

