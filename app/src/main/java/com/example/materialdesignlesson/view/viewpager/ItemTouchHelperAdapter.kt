package com.example.materialdesignlesson.view.viewpager

import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignlesson.view.RecyclerAdapter

interface ItemTouchHelperAdapter {
    fun onItemMove (fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}

interface ItemTouchHelperViewAdapter{
    fun onItemSelected()
    fun onItemClear()
}

fun interface OnStartDragListener{
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}