package com.apm.trackify.util.extension

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.util.animation.ScaleInOnTouch

fun RecyclerView.ViewHolder.getQuantityString(id: Int, quantity: Int, formatArgs: Any): String {
    return itemView.context.resources.getQuantityString(id, quantity, formatArgs)
}

fun RecyclerView.ViewHolder.scaleOnTouch() {
    itemView.setOnTouchListener(ScaleInOnTouch())
}

@SuppressLint("ClickableViewAccessibility")
fun RecyclerView.ViewHolder.setOnDragListener(view: View, itemTouchHelper: ItemTouchHelper) {
    view.setOnTouchListener { _, motionEvent ->
        when (motionEvent.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                itemTouchHelper.startDrag(this)
                true
            }
            else -> false
        }
    }
}