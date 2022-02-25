package com.example.materialdesignlesson.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.FragmentSettingsBinding
import com.example.materialdesignlesson.view.main.MainFragment
import com.example.materialdesignlesson.viewmodel.ShearedSettings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        changeTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.mainContainer, MainFragment.newInstance()).commit()
        }
    }

   private fun changeTheme(){
       when(getSharedPreferences(ShearedSettings.SHEARED_THEME, Context.MODE_PRIVATE)
           .getInt(ShearedSettings.THEME_CHOICE, ShearedSettings.settingTheme)){
            0 -> setTheme(R.style.myThemeIndigo)
            1 -> setTheme(R.style.myThemeOrange)
            2 -> setTheme(R.style.myThemeLightGreen)
        }
    }

    fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
    }
}