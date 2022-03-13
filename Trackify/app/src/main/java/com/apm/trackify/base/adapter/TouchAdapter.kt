package com.apm.trackify.base.adapter

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.drag.TouchHelperCallback

abstract class TouchAdapter<T : BaseModel>(
    dataSet: MutableList<T>
) : SimpleAdapter<T>(dataSet) {

    var canSwipeRight = false
    var canSwipeLeft = false

    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        var swipeDirs = 0
        if (canSwipeRight) swipeDirs = ItemTouchHelper.RIGHT
        if (canSwipeLeft) swipeDirs = swipeDirs or ItemTouchHelper.LEFT

        val callback = TouchHelperCallback(this, swipeDirs)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initViewHolderListeners(viewHolder: ViewHolder) {
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
    }

    abstract fun canInteractWithViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean

    abstract fun onMove(from: Int, to: Int)

    abstract fun onSwipeLeft(position: Int)

    abstract fun onSwipeRight(position: Int)
}