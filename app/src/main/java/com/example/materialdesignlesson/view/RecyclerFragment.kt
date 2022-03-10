package com.example.materialdesignlesson.view

import com.example.materialdesignlesson.databinding.FragmentRecyclerBinding
import com.example.materialdesignlesson.view.main.EarthFragment

class RecyclerFragment: BaseFragment<FragmentRecyclerBinding>(FragmentRecyclerBinding::inflate){
    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }
}