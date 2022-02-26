package com.example.materialdesignlesson.view.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.materialdesignlesson.databinding.FragmentMainBinding
import com.example.materialdesignlesson.databinding.ViewPagerBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.view.main.PODFragment

class ViewPagerFragment: BaseFragment<ViewPagerBinding>(ViewPagerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.viewPagerTabs.setupWithViewPager(binding.viewPager)
    }

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = ViewPagerFragment()
    }
}