package com.apm.trackify.util.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class DelegateAdapter<T, VH : DelegateViewHolder<T>>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, DelegateViewHolder<T>>(diffCallback) {

    override fun onBindViewHolder(holder: DelegateViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}