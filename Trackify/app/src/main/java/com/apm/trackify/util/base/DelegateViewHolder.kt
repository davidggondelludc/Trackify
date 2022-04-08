package com.apm.trackify.util.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class DelegateViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}