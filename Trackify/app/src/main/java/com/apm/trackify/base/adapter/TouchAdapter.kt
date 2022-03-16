package com.apm.trackify.base.adapter

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.drag.TouchHelperCallback

abstract class TouchAdapter<T : BaseModel>(
    dataSet: MutableList<T>,
    private val swipeDirs: Int = 0
) : SimpleAdapter<T>(dataSet) {

    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        val callback = TouchHelperCallback(this, swipeDirs)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType)

        viewHolder.itemView.findViewById<View>(R.id.dragHandle)
            ?.setOnTouchListener { _, motionEvent ->
                when (motionEvent.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        itemTouchHelper.startDrag(viewHolder)
                        true
                    }
                    else -> false
                }
            }

        return viewHolder
    }

    abstract fun canInteractWithViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean

    abstract fun onMove(from: Int, to: Int)

    abstract fun onSwipeLeft(position: Int)

    abstract fun onSwipeRight(position: Int)
}