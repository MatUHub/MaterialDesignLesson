package com.example.materialdesignlesson.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.ActivityMainBinding
import com.example.materialdesignlesson.view.chips.SettingsFragment
import com.example.materialdesignlesson.view.main.PODFragment
import com.example.materialdesignlesson.view.viewpager.ViewPagerFragment
import com.example.materialdesignlesson.viewmodel.ShearedSettings

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        changeTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, ViewPagerFragment.newInstance()).commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomViewPicOfDay -> {
                    navigateTo(ViewPagerFragment())
                    true
                }
                R.id.bottomViewEarth -> {
                    true
                }
                R.id.bottomViewMars -> {
                    true
                }
                R.id.bottomViewFavorite -> {
                    true
                }
                R.id.bottomViewSettings -> {
                    navigateTo(SettingsFragment())
                    true
                }
                else -> true
            }
        }
    }

    private fun changeTheme() {
        when (getSharedPreferences(ShearedSettings.SHEARED_THEME, Context.MODE_PRIVATE)
            .getInt(ShearedSettings.THEME_CHOICE, ShearedSettings.settingTheme)) {
            0 -> setTheme(R.style.myThemeIndigo)
            1 -> setTheme(R.style.myThemeOrange)
            2 -> setTheme(R.style.myThemeLightGreen)
        }
    }

    fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
    }
}