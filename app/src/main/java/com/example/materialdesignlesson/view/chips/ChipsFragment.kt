package com.example.materialdesignlesson.view.chips

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.materialdesignlesson.databinding.FragmentChipsBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.google.android.material.chip.Chip

class ChipsFragment: BaseFragment<FragmentChipsBinding>(FragmentChipsBinding::inflate) {

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = ChipsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //реализации слушателя на чипах
        binding.chipGroup.setOnCheckedChangeListener{ group, checkedId ->
            binding.chipGroup.findViewById<Chip>(checkedId)?.let{ it ->
                myToast("${it.text} ${checkedId}")
            }
        }

        binding.chipEntry.setOnCloseIconClickListener{
            myToast("chipEntry icon close")
        }
    }
    fun myToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}