package com.apm.trackify.ui.playlists.tracks.adapter.drag

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistTrackDragItemBinding
import com.apm.trackify.ui.playlists.tracks.PlaylistTracksViewModel
import com.apm.trackify.ui.playlists.tracks.view.TrackViewHolder
import com.apm.trackify.util.animation.SwipeAnimator

class ItemTouchHelperCallback(
    private val viewModel: PlaylistTracksViewModel
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    ItemTouchHelper.RIGHT
) {

    /** Variable to prevent view desynchronization when user swipes a view holder too fast **/
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

            // Animation for the behind layout
            val binding = PlaylistTrackDragItemBinding.bind(viewHolder.itemView)
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

    /** Disables the option to drag the view holder. By code it's assigned to an ImageButton */
    override fun isLongPressDragEnabled(): Boolean = false
}