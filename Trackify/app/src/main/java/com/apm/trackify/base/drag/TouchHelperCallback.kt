package com.apm.trackify.base.drag

import android.graphics.Canvas
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.adapter.TouchAdapter
import com.apm.trackify.base.extensions.getOrDefaultSet
import kotlin.math.abs

internal class TouchHelperCallback<T : BaseModel>(
    private val adapter: TouchAdapter<T>,
    swipeDirs: Int = 0
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    swipeDirs
) {

    private val animations = HashMap<Int, TouchHelperAnimator>()

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return if (adapter.canInteractWithViewHolder(viewHolder) &&
            adapter.canInteractWithViewHolder(target)
        ) {
            adapter.onMove(viewHolder.adapterPosition, target.adapterPosition)
            true
        } else false
    }

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (adapter.canInteractWithViewHolder(viewHolder)) super.getSwipeDirs(
            recyclerView,
            viewHolder
        ) else 0
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (adapter.canInteractWithViewHolder(viewHolder)) {
            when (direction) {
                ItemTouchHelper.RIGHT -> adapter.onSwipeRight(viewHolder.adapterPosition)
                ItemTouchHelper.LEFT -> adapter.onSwipeLeft(viewHolder.adapterPosition)
            }
        }
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_SWIPE -> {
                val animation = animations.getOrDefaultSet(
                    viewHolder.hashCode(),
                    TouchHelperAnimator(viewHolder.itemView)
                )
                when {
                    abs(dX) > (viewHolder.itemView.width * 0.35f) -> animation.drawCircularReveal(dX)
                    abs(dX) < (viewHolder.itemView.width * 0.05f) -> animation.setAnimationIdle()
                    abs(dX) < (viewHolder.itemView.width * 0.35f) -> animation.initializeSwipe(dX)
                }

                getDefaultUIUtil().onDraw(
                    canvas,
                    recyclerView,
                    viewHolder.itemView.findViewById(R.id.content),
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
            ItemTouchHelper.ACTION_STATE_DRAG -> {
                if (isCurrentlyActive) {
                    val newElevation = 5f + findMaxElevation(recyclerView, viewHolder.itemView)
                    ViewCompat.setElevation(viewHolder.itemView, newElevation)
                }
                viewHolder.itemView.translationY = dY
            }
        }
    }

    private fun findMaxElevation(recyclerView: RecyclerView, view: View): Float {
        var max = 0f
        for (i in 0 until recyclerView.childCount) {
            val child = recyclerView.getChildAt(i)
            if (child === view) {
                continue
            }
            val elevation = ViewCompat.getElevation(child)
            if (elevation > max) {
                max = elevation
            }
        }
        return max
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        getDefaultUIUtil().clearView(viewHolder.itemView.findViewById(R.id.content))
        animations.remove(viewHolder.hashCode())
    }

    override fun isLongPressDragEnabled(): Boolean = false
}