package com.apm.trackify.base.drag

import android.graphics.Canvas
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.ItemPlaylistTrackBinding
import kotlin.math.abs

class TouchHelperAdapterCallback(private val adapter: TouchAdapter) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {

    private val animationsController = TouchHelperAnimationController()

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
        } else {
            false
        }
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
                val viewWidth = viewHolder.itemView.width
                when {
                    abs(dX) > (viewWidth * 0.35f) -> {
                        animationsController.drawCircularReveal(viewHolder, dX)
                    }
                    abs(dX) < (viewWidth * 0.05f) -> {
                        animationsController.setAnimationIdle()
                    }
                    abs(dX) < (viewWidth * 0.35f) -> {
                        animationsController.initializeSwipe(viewHolder, dX)
                    }
                }

                val binding = ItemPlaylistTrackBinding.bind(viewHolder.itemView)
                getDefaultUIUtil().onDraw(
                    canvas,
                    recyclerView,
                    binding.content,
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

    private fun findMaxElevation(recyclerView: RecyclerView, itemView: View): Float {
        val childCount = recyclerView.childCount
        var max = 0f
        for (i in 0 until childCount) {
            val child = recyclerView.getChildAt(i)
            if (child === itemView) {
                continue
            }
            val elevation = ViewCompat.getElevation(child)
            if (elevation > max) {
                max = elevation
            }
        }

        return max
    }

    override fun isLongPressDragEnabled(): Boolean = false
}