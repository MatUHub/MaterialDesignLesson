package com.example.materialdesignlesson.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.FragmentMainBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.viewmodel.PODData
import com.example.materialdesignlesson.viewmodel.PODViewModel


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {



    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer { renderData(it)
        })
        viewModel.sendRequest()
    }

    fun renderData(podData: PODData) {
        when (podData) {
            is PODData.Error -> {

            }
            is PODData.Loading -> {

            }
            is PODData.Success -> {
                binding.imageView.load(podData.serverResponse.url){

                }
            }
        }
    }

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = MainFragment()
    }

}