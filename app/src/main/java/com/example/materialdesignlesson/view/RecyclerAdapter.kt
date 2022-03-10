package com.example.materialdesignlesson.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignlesson.databinding.RecyclerItemEarthBinding
import com.example.materialdesignlesson.databinding.RecyclerItemMarsBinding
import com.example.materialdesignlesson.repository.DataRecycle
import com.example.materialdesignlesson.repository.OnListItemClickListener
import com.example.materialdesignlesson.repository.TYPE_EARTH
import com.example.materialdesignlesson.repository.TYPE_MARS

class RecyclerAdapter(private val onListItemClickListener: OnListItemClickListener, private var data: List<DataRecycle>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_EARTH) {
            val itemBinding: RecyclerItemEarthBinding = RecyclerItemEarthBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
            return EarthViewHolder(itemBinding.root)

        } else {
            val itemBinding: RecyclerItemMarsBinding = RecyclerItemMarsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
            return MarsViewHolder(itemBinding.root)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(getItemViewType(position) == TYPE_EARTH){
           (holder as EarthViewHolder).bind(data[position])
       } else {
           (holder as MarsViewHolder).bind(data[position])
       }
    }

    override fun getItemCount(): Int {
       return data.size
    }

      inner class EarthViewHolder(view: View): RecyclerView.ViewHolder(view){
           fun bind(data: DataRecycle){
               RecyclerItemEarthBinding.bind(itemView).apply {
                   earthImageView.setOnClickListener{
                       onListItemClickListener.onItemClick(data)
                   }
               }
           }
       }

      inner  class MarsViewHolder(view: View): RecyclerView.ViewHolder(view){
            fun bind(data: DataRecycle){
                RecyclerItemMarsBinding.bind(itemView).apply {
                    marsImageView.setOnClickListener{
                        onListItemClickListener.onItemClick(data)
                    }
                }
            }
        }
}