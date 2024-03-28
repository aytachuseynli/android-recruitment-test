package com.aytachuseynli.algoritmatask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aytachuseynli.algoritmatask.common.utils.GenericDiffUtil
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import com.aytachuseynli.algoritmatask.databinding.ItemSocketBinding

class SocketAdapter : ListAdapter<SocketModel, SocketAdapter.ViewHolder>(
    GenericDiffUtil<SocketModel>(
        myItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        myContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSocketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSocketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(socketModel: SocketModel) {
            binding.socketModel = socketModel
            binding.executePendingBindings()
        }
    }
}
