package com.example.materialdesignlesson.view

import android.graphics.Color
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignlesson.databinding.RecyclerItemEarthBinding
import com.example.materialdesignlesson.databinding.RecyclerItemHeaderBinding
import com.example.materialdesignlesson.databinding.RecyclerItemMarsBinding
import com.example.materialdesignlesson.repository.*
import com.example.materialdesignlesson.view.viewpager.ItemTouchHelperAdapter
import com.example.materialdesignlesson.view.viewpager.ItemTouchHelperViewAdapter
import com.example.materialdesignlesson.view.viewpager.OnStartDragListener

class RecyclerAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var dataSet: MutableList<Pair<Int, DataRecycle>>,
    private val onStartDragListener: OnStartDragListener
) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(), ItemTouchHelperAdapter {

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].second.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {

            TYPE_EARTH -> {
                val itemBinding: RecyclerItemEarthBinding = RecyclerItemEarthBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return EarthViewHolder(itemBinding.root)
            }

            TYPE_MARS -> {
                val itemBinding: RecyclerItemMarsBinding = RecyclerItemMarsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return MarsViewHolder(itemBinding.root)
            }

            TYPE_HEADER -> {
                val itemBinding: RecyclerItemHeaderBinding = RecyclerItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return HeaderViewHolder(itemBinding.root)
            }

            else -> {
                val itemBinding: RecyclerItemHeaderBinding = RecyclerItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return HeaderViewHolder(itemBinding.root)
            }
        }

    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addItem() {
        dataSet.add(generateNewItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateNewItem() = Pair(ITEM_CLOSE, DataRecycle("new Mars", type = TYPE_MARS))

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Int, DataRecycle>)
    }


    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Int, DataRecycle>) {
            RecyclerItemEarthBinding.bind(itemView).apply {
                earthImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data.second)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewAdapter {
        override fun bind(data: Pair<Int, DataRecycle>) {
            RecyclerItemMarsBinding.bind(itemView).apply {
                marsImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data.second)
                }

                marsTextView.setOnClickListener{
                    dataSet[layoutPosition] = dataSet[layoutPosition].let {
                        val currentSet = if(it.first == ITEM_CLOSE) ITEM_OPEN else ITEM_CLOSE
                        Pair(currentSet, it.second)
                    }
                    notifyItemChanged(layoutPosition)
                }
                textViewItemMars.visibility = if(data.first == ITEM_CLOSE) View.GONE else View.VISIBLE

                addItemImageView.setOnClickListener { addItemByPosition() }
                deleteItemImageView.setOnClickListener { deleteItem() }
                moveItemDown.setOnClickListener {
                    if (layoutPosition <= dataSet.size) {
                        dataSet.removeAt(layoutPosition).apply {
                            dataSet.add(layoutPosition + 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition + 1)
                    }
                }
                moveItemUp.setOnClickListener {

                    if (layoutPosition > 1) {
                        dataSet.removeAt(layoutPosition).apply {
                            dataSet.add(layoutPosition - 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition - 1)
                    }
                }

                dragHandle.setOnTouchListener { view, motionEvent ->
                    if(MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN){
                        onStartDragListener.onStartDrag(this@MarsViewHolder)
                    }
                    false
                }
            }

        }

        private fun deleteItem() {
            dataSet.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        private fun addItemByPosition() {
            dataSet.add(layoutPosition + 1, generateNewItem())
            notifyItemInserted(layoutPosition + 1)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.GREEN)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }


    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Int, DataRecycle>) {
            RecyclerItemHeaderBinding.bind(itemView).apply {
                headerItemView.setOnClickListener {
                    onListItemClickListener.onItemClick(data.second)
                }
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if(toPosition > 0) {
            dataSet.removeAt(fromPosition).apply {
                dataSet.add(toPosition, this)
            }
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    override fun onItemDismiss(position: Int) {
       dataSet.removeAt(position)
        notifyItemRemoved(position)
    }
}