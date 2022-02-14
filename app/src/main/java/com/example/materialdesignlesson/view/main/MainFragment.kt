package com.example.materialdesignlesson.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.FragmentMainBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.viewmodel.PODData
import com.example.materialdesignlesson.viewmodel.PODViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {


    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
        //задание настроек для bottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)

        // открытие bottomSheet на половину
        //bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        //фиксированные состояния bottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> myToast("состояние DRAGGING")
                    BottomSheetBehavior.STATE_COLLAPSED -> myToast("состояние COLLAPSED")
                    BottomSheetBehavior.STATE_EXPANDED -> myToast("состояние EXPANDED")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> myToast("состояние HALF_EXPANDED")
                    BottomSheetBehavior.STATE_HIDDEN -> myToast("состояние HIDDEN")
                    BottomSheetBehavior.STATE_SETTLING -> myToast("состояние SETTLING")
                }
            }

            //указывает уровень открытия bottomSheet через slideOffset
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("log", "slide $slideOffset")
            }

        })
    }

    private fun renderData(podData: PODData) {
        when (podData) {
            is PODData.Error -> {

            }
            is PODData.Loading -> {

            }
            is PODData.Success -> {
                binding.imageView.load(podData.serverResponse.url) {
                    placeholder(R.drawable.ic_no_photo_vector)

                }
            }
        }
    }

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    fun myToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}
