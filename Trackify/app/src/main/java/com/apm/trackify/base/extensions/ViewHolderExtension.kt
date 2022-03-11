package com.apm.trackify.base.extensions

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.base.drag.ITouchListener

@SuppressLint("ClickableViewAccessibility")
fun RecyclerView.ViewHolder.setOnTouchListener(id: Int, touchListener: ITouchListener) {
    itemView.findViewById<View>(id)?.setOnTouchListener { _, motionEvent ->
        when (motionEvent.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                touchListener.onStartDrag(this)
                true
            }
            else -> false
        }
    }
}