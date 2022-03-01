package com.example.materialdesignlesson.view.main

import com.example.materialdesignlesson.databinding.FragmentEarthBinding
import com.example.materialdesignlesson.databinding.FragmentFavouritBinding
import com.example.materialdesignlesson.view.BaseFragment

class FavouritFragment : BaseFragment<FragmentFavouritBinding>(FragmentFavouritBinding::inflate) {

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = FavouritFragment()
    }
}