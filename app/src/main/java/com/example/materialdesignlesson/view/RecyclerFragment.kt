package com.example.materialdesignlesson.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignlesson.databinding.FragmentRecyclerBinding
import com.example.materialdesignlesson.repository.*
import com.example.materialdesignlesson.view.viewpager.ItemTouchHelperAdapter
import com.example.materialdesignlesson.view.viewpager.ItemTouchHelperViewAdapter

class RecyclerFragment : BaseFragment<FragmentRecyclerBinding>(FragmentRecyclerBinding::inflate) {

    lateinit var adapter: RecyclerAdapter

    val data = arrayListOf(
        Pair(ITEM_CLOSE, DataRecycle("Header", type = TYPE_HEADER)),
        Pair(ITEM_CLOSE, DataRecycle("Earth", type = TYPE_EARTH)),
        Pair(ITEM_CLOSE, DataRecycle("Earth", type = TYPE_EARTH)),
        Pair(ITEM_CLOSE, DataRecycle("Mars", type = TYPE_MARS)),
        Pair(ITEM_CLOSE, DataRecycle("Earth", type = TYPE_EARTH)),
        Pair(ITEM_CLOSE, DataRecycle("Mars", type = TYPE_MARS)),
        Pair(ITEM_CLOSE, DataRecycle("Earth", type = TYPE_EARTH)),
        Pair(ITEM_CLOSE, DataRecycle("Earth", type = TYPE_EARTH))
    )




    companion object {
        //@JvmStatic указывает о статическом поле, без учета companion object
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       adapter = RecyclerAdapter({
            Toast.makeText(
                requireContext(),
                it.someText,
                Toast.LENGTH_SHORT
            ).show()
        }, data)

        binding.recyclerView.adapter = adapter

        binding.recyclerFAB.setOnClickListener {
            adapter.addItem()
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
        }

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)
    }

  inner  class ItemTouchHelperCallback(private val adapter: RecyclerAdapter): ItemTouchHelper.Callback(){

      override fun isLongPressDragEnabled(): Boolean {
          return true
      }

      override fun isItemViewSwipeEnabled(): Boolean {
          return super.isItemViewSwipeEnabled()
      }

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipe = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(drag, swipe)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.onItemDismiss(viewHolder.adapterPosition)
        }

      override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
          if(viewHolder !is RecyclerAdapter.MarsViewHolder){
              return  super.onSelectedChanged(viewHolder, actionState)
          }
      }

      override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
          if(viewHolder !is RecyclerAdapter.MarsViewHolder){
              return super.clearView(recyclerView, viewHolder)
          }
      }
    }
}