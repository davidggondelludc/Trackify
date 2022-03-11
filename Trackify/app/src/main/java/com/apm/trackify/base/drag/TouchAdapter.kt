package com.apm.trackify.base.drag

import androidx.recyclerview.widget.RecyclerView

interface TouchAdapter {
    fun canInteractWithViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean

    fun onMove(from: Int, to: Int)

    fun onSwipeLeft(position: Int)

    fun onSwipeRight(position: Int)
}