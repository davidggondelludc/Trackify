package com.apm.trackify.base.drag

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface ITouchListener {

    fun setupListener(view: RecyclerView)

    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}

class TouchListener() : ITouchListener {

    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun setupListener(view: RecyclerView) {
        val callback = TouchHelperAdapterCallback(view.adapter as TouchAdapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(view)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }
}