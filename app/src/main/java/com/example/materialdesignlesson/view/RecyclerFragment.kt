package com.example.materialdesignlesson.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.materialdesignlesson.databinding.FragmentRecyclerBinding
import com.example.materialdesignlesson.repository.DataRecycle
import com.example.materialdesignlesson.repository.OnListItemClickListener
import com.example.materialdesignlesson.repository.TYPE_EARTH
import com.example.materialdesignlesson.repository.TYPE_MARS

class RecyclerFragment: BaseFragment<FragmentRecyclerBinding>(FragmentRecyclerBinding::inflate){

    val data = arrayListOf(
        DataRecycle("Earth", type = TYPE_EARTH),
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
        binding.recyclerView.adapter = RecyclerAdapter(OnListItemClickListener{ Toast.makeText(requireContext(), it.someText, Toast.LENGTH_SHORT).show()}, data)
    }
}