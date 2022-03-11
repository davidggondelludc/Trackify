package com.apm.trackify.base.drag

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import kotlin.math.abs

class TouchHelperAdapterCallback(private val adapter: TouchAdapter) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
    ) {

    private val animations = HashMap<RecyclerView.ViewHolder, TouchHelperAnimationController>()

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

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        if (adapter.canInteractWithViewHolder(viewHolder)) {
            return super.getSwipeDirs(recyclerView, viewHolder)
        }
        return 0
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
                var animation = animations[viewHolder]
                if (animation == null) {
                    animation = TouchHelperAnimationController()
                    animations[viewHolder] = animation
                }

                val viewWidth = viewHolder.itemView.width
                when {
                    abs(dX) > (viewWidth * 0.35f) -> {
                        animation.drawCircularReveal(viewHolder, dX)
                    }
                    abs(dX) < (viewWidth * 0.05f) -> {
                        animation.setAnimationIdle(viewHolder)
                    }
                    abs(dX) < (viewWidth * 0.35f) -> {
                        animation.initializeSwipe(viewHolder, dX)
                    }
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
                super.onChildDraw(
                    canvas,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        getDefaultUIUtil().clearView(viewHolder.itemView.findViewById(R.id.content))
        animations.remove(viewHolder)
    }

    override fun isLongPressDragEnabled(): Boolean = false
}