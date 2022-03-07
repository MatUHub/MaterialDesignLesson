package com.example.materialdesignlesson.view.main

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.materialdesignlesson.databinding.FragmentEarthBinding
import com.example.materialdesignlesson.databinding.FragmentMarsBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.view.viewpager.ViewPagerFragment

class EarthFragment : BaseFragment<FragmentEarthBinding>(FragmentEarthBinding::inflate) {

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = EarthFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var flag = false

        binding.buttonAnimation.setOnClickListener{

            flag = !flag

            val changeBounds = ChangeBounds()

            changeBounds.duration = 3000

            TransitionManager.beginDelayedTransition(binding.frameEarth, changeBounds)

            val params = binding.buttonAnimation.layoutParams as FrameLayout.LayoutParams
            binding.buttonAnimation.layoutParams = params
            params.gravity = if(flag){
                Gravity.BOTTOM or Gravity.END
            } else {
                Gravity.TOP or Gravity.START
            }

            val params1 = binding.buttonAnimation1.layoutParams as FrameLayout.LayoutParams
            binding.buttonAnimation1.layoutParams = params1
            params1.gravity = if(flag){
                Gravity.BOTTOM or Gravity.START
            } else {
                Gravity.TOP or Gravity.END
            }

            val params2 = binding.buttonAnimation2.layoutParams as FrameLayout.LayoutParams
            binding.buttonAnimation2.layoutParams = params2
            params2.gravity = if(flag){
                Gravity.BOTTOM or Gravity.CENTER
            } else {
                Gravity.TOP or Gravity.CENTER
            }


        }
    }


}