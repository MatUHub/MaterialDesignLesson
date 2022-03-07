package com.example.materialdesignlesson.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

    class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_animation, parent, false))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         holder.itemView.setOnClickListener{

         }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }
}