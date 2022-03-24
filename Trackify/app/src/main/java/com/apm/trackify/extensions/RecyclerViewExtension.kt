package com.apm.trackify.extensions

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.utils.animation.ScaleInOnTouch

fun RecyclerView.ViewHolder.getQuantityString(id: Int, quantity: Int): String {
    return itemView.context.resources.getQuantityString(id, quantity)
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