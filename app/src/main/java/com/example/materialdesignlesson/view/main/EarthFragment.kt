package com.example.materialdesignlesson.view.main

import com.example.materialdesignlesson.databinding.FragmentEarthBinding
import com.example.materialdesignlesson.databinding.FragmentMarsBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.view.viewpager.ViewPagerFragment

class EarthFragment : BaseFragment<FragmentEarthBinding>(FragmentEarthBinding::inflate) {

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = EarthFragment()
    }
}