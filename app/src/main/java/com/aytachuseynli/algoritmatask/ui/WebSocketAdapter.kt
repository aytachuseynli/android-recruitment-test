package com.aytachuseynli.algoritmatask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.aytachuseynli.algoritmatask.common.utils.GenericDiffUtil
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import com.aytachuseynli.algoritmatask.databinding.ItemSocketBinding
import okhttp3.internal.platform.android.SocketAdapter


class WebSocketAdapter : ListAdapter<SocketModel, WebSocketAdapter.ViewHolder>(
    GenericDiffUtil<SocketModel>(
        myItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
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