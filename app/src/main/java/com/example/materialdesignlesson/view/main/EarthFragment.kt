package com.example.materialdesignlesson.view.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
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

    private var flag = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var flag = false

        binding.buttonAnimation.setOnClickListener {

            flag = !flag

            val changeBounds = ChangeBounds()

            changeBounds.duration = 3000

            TransitionManager.beginDelayedTransition(binding.frameEarth, changeBounds)

            val params = binding.buttonAnimation.layoutParams as FrameLayout.LayoutParams
            binding.buttonAnimation.layoutParams = params
            params.gravity = if (flag) {
                Gravity.BOTTOM or Gravity.END
            } else {
                Gravity.TOP or Gravity.START
            }

            val params1 = binding.buttonAnimation1.layoutParams as FrameLayout.LayoutParams
            binding.buttonAnimation1.layoutParams = params1
            params1.gravity = if (flag) {
                Gravity.BOTTOM or Gravity.START
            } else {
                Gravity.TOP or Gravity.END
            }

            val params2 = binding.buttonAnimation2.layoutParams as FrameLayout.LayoutParams
            binding.buttonAnimation2.layoutParams = params2
            params2.gravity = if (flag) {
                Gravity.BOTTOM or Gravity.CENTER
            } else {
                Gravity.TOP or Gravity.CENTER
            }
        }



        binding.containerOne.alpha = 0f
        binding.containerTwo.alpha = 0f
        binding.containerThree.alpha = 0f
        binding.containerFour.alpha = 0f

        binding.picEarth.alpha = 1f

        binding.containerOne.isClickable = false
        binding.containerTwo.isClickable = false
        binding.containerThree.isClickable = false
        binding.containerFour.isClickable = false

        binding.fab.setOnClickListener {
            flag = !flag

            val duration = 1000L
            if (flag) {
                binding.picEarth.animate().alpha(0f)
                binding.containerOne.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerOne.isClickable = true
                            super.onAnimationEnd(animation)
                        }
                    })

                binding.containerTwo.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerTwo.isClickable = true
                            super.onAnimationEnd(animation)
                        }
                    })

                binding.containerThree.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerThree.isClickable = true
                            super.onAnimationEnd(animation)
                        }
                    })

                binding.containerFour.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerFour.isClickable = true
                            super.onAnimationEnd(animation)
                        }
                    })

                ObjectAnimator.ofFloat(binding.iconPlus, View.ROTATION, 0f, 400f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerOne, View.TRANSLATION_Y, 0f, -130f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerThree, View.TRANSLATION_Y, 0f, 130f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerTwo, View.TRANSLATION_X, 0f, 220f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerFour, View.TRANSLATION_X, 0f, -220f)
                    .setDuration(duration).start()
            } else {
                ObjectAnimator.ofFloat(binding.iconPlus, View.ROTATION, 400f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerOne, View.TRANSLATION_Y, -130f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerThree, View.TRANSLATION_Y, 130f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerTwo, View.TRANSLATION_X, 220f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.containerFour, View.TRANSLATION_X, -220f, 0f)
                    .setDuration(duration).start()


                binding.picEarth.animate().alpha(1f)

                binding.containerOne.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerOne.isClickable = false
                            super.onAnimationEnd(animation)
                        }
                    })

                binding.containerTwo.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerTwo.isClickable = false
                            super.onAnimationEnd(animation)
                        }
                    })

                binding.containerThree.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerThree.isClickable = false
                            super.onAnimationEnd(animation)
                        }
                    })

                binding.containerFour.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.containerFour.isClickable = false
                            super.onAnimationEnd(animation)
                        }
                    })
            }
        }
    }


}