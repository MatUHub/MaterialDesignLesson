package com.example.materialdesignlesson.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignlesson.databinding.RecyclerItemEarthBinding
import com.example.materialdesignlesson.databinding.RecyclerItemHeaderBinding
import com.example.materialdesignlesson.databinding.RecyclerItemMarsBinding
import com.example.materialdesignlesson.repository.*

class RecyclerAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<DataRecycle>
) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {

            TYPE_EARTH -> {
                val itemBinding: RecyclerItemEarthBinding = RecyclerItemEarthBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
                return EarthViewHolder(itemBinding.root)
            }

            TYPE_MARS -> {
                val itemBinding: RecyclerItemMarsBinding = RecyclerItemMarsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
                return MarsViewHolder(itemBinding.root)
            }

            TYPE_HEADER -> {
                val itemBinding: RecyclerItemHeaderBinding = RecyclerItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
                return HeaderViewHolder(itemBinding.root)
            }

            else -> {
                val itemBinding: RecyclerItemHeaderBinding = RecyclerItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
                return HeaderViewHolder(itemBinding.root)
            }
        }

    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem() {
        data.add(generateNewItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateNewItem(): DataRecycle {
        return DataRecycle("new Mars", type = TYPE_MARS)
    }

    abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view){
        abstract fun bind(data: DataRecycle)
    }


    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
       override fun bind(data: DataRecycle) {
            RecyclerItemEarthBinding.bind(itemView).apply {
                earthImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: DataRecycle) {
            RecyclerItemMarsBinding.bind(itemView).apply {
                marsImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: DataRecycle) {
            RecyclerItemHeaderBinding.bind(itemView).apply {
                headerItemView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }
}