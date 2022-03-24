package com.apm.trackify.ui.playlist.details.adapter.drag

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsDetailsTrackItemBinding
import com.apm.trackify.ui.playlist.details.PlaylistDetailsViewModel
import com.apm.trackify.ui.playlist.details.view.TrackViewHolder
import com.apm.trackify.utils.animation.SwipeAnimator

class ItemTouchHelperCallback(
    private val viewModel: PlaylistDetailsViewModel
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    ItemTouchHelper.RIGHT
) {

    // Variable to prevent dsynchronize the view when the user swipe too fast
    private var canSwipe = true

    private val animator = SwipeAnimator()

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val initialPosition = viewHolder.bindingAdapterPosition
        val finalPosition = target.bindingAdapterPosition

        return if (initialPosition != finalPosition && viewHolder is TrackViewHolder) {
            viewModel.move(initialPosition, finalPosition)
            true
        } else false
    }

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (canSwipe && viewHolder is TrackViewHolder) {
            super.getSwipeDirs(recyclerView, viewHolder)
        } else 0
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        canSwipe = false
        viewModel.remove(viewHolder.bindingAdapterPosition)
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
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            canvas.save()

            // Apply a clip rectangle with the calculated area to draw inside it
            val left = viewHolder.itemView.left
            val top = viewHolder.itemView.top + viewHolder.itemView.translationY.toInt()
            val right = viewHolder.itemView.right
            val bottom = viewHolder.itemView.bottom + viewHolder.itemView.translationY.toInt()

            canvas.clipRect(left, top, right, bottom)

            // Animate the behind layout
            val binding = PlaylistsDetailsTrackItemBinding.bind(viewHolder.itemView)
            val behindLayout = binding.swipe.root

            when {
                dX < viewHolder.itemView.width * 0.05 -> animator.setIdle(behindLayout)
                dX < viewHolder.itemView.width * 0.35f -> animator.initSwipe(behindLayout)
                else -> animator.drawCircularReveal(behindLayout)
            }

            // Draw the custom layout behind the item
            val width = right - left
            val height = bottom - top

            if (behindLayout.measuredWidth != width || behindLayout.measuredHeight != height) {
                behindLayout.measure(
                    View.MeasureSpec.makeMeasureSpec(
                        width,
                        View.MeasureSpec.EXACTLY
                    ),
                    View.MeasureSpec.makeMeasureSpec(
                        height,
                        View.MeasureSpec.EXACTLY
                    )
                )
            }

            behindLayout.layout(left, top, right, bottom)
            canvas.save()
            canvas.translate(left.toFloat(), top.toFloat())
            behindLayout.draw(canvas)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        canSwipe = true
    }

    // Disable the option of drag the view holder, this is done a custom button
    override fun isLongPressDragEnabled(): Boolean = false
}