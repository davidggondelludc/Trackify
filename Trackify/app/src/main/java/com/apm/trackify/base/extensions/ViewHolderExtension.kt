package com.apm.trackify.base.extensions

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.base.drag.IDragListener

fun RecyclerView.ViewHolder.setOnDragListener(dragHandleId: Int, dragListener: IDragListener) {
    itemView.findViewById<View>(dragHandleId)?.setOnTouchListener { _, event ->
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                dragListener.onStartDrag(this)
                true
            }
            else -> false
        }
    }
}