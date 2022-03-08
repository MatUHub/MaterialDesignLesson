package com.example.materialdesignlesson.view.main

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.materialdesignlesson.R
import com.example.materialdesignlesson.databinding.FragmentMarsBinding
import com.example.materialdesignlesson.view.BaseFragment
import com.example.materialdesignlesson.view.viewpager.ViewPagerFragment

class MarsFragment : BaseFragment<FragmentMarsBinding>(FragmentMarsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = MyAdapter()

    }

    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = MarsFragment()
    }

    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_item_animation, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val transitionSet = TransitionSet()
                val explode = Explode()
                val fade = Fade()
                fade.duration = 99999999
                explode.duration = 3000
                val rect1 = Rect()
                explode.excludeTarget(it, true)
                it.getGlobalVisibleRect(rect1)
                val rect2 = Rect(
                    it.x.toInt(),
                    it.y.toInt(),
                    it.x.toInt() + it.width,
                    it.y.toInt() + it.height
                )
                explode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return rect1
                    }
                }
                transitionSet.addTransition(explode)
                transitionSet.addTransition(fade)
                TransitionManager.beginDelayedTransition(binding.containerAnimation, transitionSet)
                binding.recyclerView.adapter = null
            }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }
}