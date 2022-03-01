package com.example.materialdesignlesson.view.main

import com.example.materialdesignlesson.databinding.FragmentMarsBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.view.viewpager.ViewPagerFragment

class MarsFragment : BaseFragment<FragmentMarsBinding>(FragmentMarsBinding::inflate) {

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = MarsFragment()
    }
}