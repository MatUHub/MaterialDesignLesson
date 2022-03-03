package com.example.materialdesignlesson.view.chips

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.FragmentSettingsBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.viewmodel.ShearedSettings
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    var visible = true

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = SettingsFragment()

    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonVisibility.setBackgroundColor(R.color.red)

        binding.themeButton1.setOnClickListener {
            setThemeInSheredPref(0)
        }
        binding.themeButton2.setOnClickListener {
            setThemeInSheredPref(1)
        }
        binding.themeButton3.setOnClickListener {
            setThemeInSheredPref(2)
        }

        binding.buttonVisibility.setOnClickListener {

            when (visible) {
                true -> {
                    binding.groupButtons.visibility = View.GONE
                    binding.buttonVisibility.setText("OFF")
                    binding.buttonVisibility.setBackgroundResource(R.color.red)
                    visible = false
                }
                false -> {
                    binding.groupButtons.visibility = View.VISIBLE
                    binding.buttonVisibility.setText("ON")
                    binding.buttonVisibility.setBackgroundResource(R.color.green)

                    visible = true
                }

            }

        }
    }

    private fun myToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun setThemeInSheredPref(themeId: Int){
        ShearedSettings.settingTheme = themeId
        val themePref = requireActivity().getSharedPreferences(
            ShearedSettings.SHEARED_THEME, Context.MODE_PRIVATE
        )
        val editor = themePref.edit()
        editor.putInt(ShearedSettings.THEME_CHOICE,ShearedSettings.settingTheme).apply()
        myToast("Установлна ${themeId + 1} тема")
        requireActivity().recreate()
    }

}
