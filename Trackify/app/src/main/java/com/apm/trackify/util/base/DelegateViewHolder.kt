package com.apm.trackify.util.base

import android.content.Context
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class DelegateViewHolder<T>(open val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val context: Context = itemView.context
    val resources: Resources = itemView.resources

    abstract fun bind(item: T)
}