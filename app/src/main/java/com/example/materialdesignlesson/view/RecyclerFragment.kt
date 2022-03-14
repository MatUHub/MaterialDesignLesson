package com.example.materialdesignlesson.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.materialdesignlesson.databinding.FragmentRecyclerBinding
import com.example.materialdesignlesson.repository.*

class RecyclerFragment : BaseFragment<FragmentRecyclerBinding>(FragmentRecyclerBinding::inflate) {

    val data = arrayListOf(
        DataRecycle("Заголовок", type = TYPE_HEADER),
        DataRecycle("Earth", type = TYPE_EARTH),
        DataRecycle("Earth", type = TYPE_EARTH),
        DataRecycle("Mars", "", type = TYPE_MARS),
        DataRecycle("Earth", type = TYPE_EARTH),
        DataRecycle("Earth", type = TYPE_EARTH),
        DataRecycle("Mars", "", type = TYPE_MARS)
    )


    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecyclerAdapter( {
            Toast.makeText(
                requireContext(),
                it.someText,
                Toast.LENGTH_SHORT
            ).show()
        }, data)

        binding.recyclerView.adapter = adapter

        binding.recyclerFAB.setOnClickListener{
            adapter.addItem()
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
        }
    }
}