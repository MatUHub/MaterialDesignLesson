package com.example.materialdesignlesson.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.BottomNavigationLayoutBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private var _binding: BottomNavigationLayoutBinding? = null
    val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_one -> {
                    myToast("navigation_one")
                }
                R.id.navigation_two -> {
                    myToast("navigation_two")
                }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun myToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}