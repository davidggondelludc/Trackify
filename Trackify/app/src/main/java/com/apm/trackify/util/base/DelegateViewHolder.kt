package com.apm.trackify.util.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}