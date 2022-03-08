package com.apm.trackify.base.drag

import android.graphics.Canvas
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.ItemPlaylistTrackBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

class TouchHelperAdapterCallback(private val adapter: TouchableAdapter, swipeDirs: Int) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        swipeDirs
    ), CoroutineScope by MainScope() {

    companion object {
        private const val SWIPE_DURATION = DEFAULT_SWIPE_ANIMATION_DURATION.toLong() - 50
    }

    private val animationsController = TouchHelperAnimationController()

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (adapter.canInteractWithViewHolder(viewHolder.itemViewType) &&
            adapter.canInteractWithViewHolder(target.itemViewType)
        ) {
            adapter.onMoved(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }
        return false
    }

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        if (adapter.canInteractWithViewHolder(viewHolder.itemViewType)) {
            return super.getSwipeDirs(recyclerView, viewHolder)
        }
        return 0
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (adapter.canInteractWithViewHolder(viewHolder.itemViewType)) {
            when (direction) {
                ItemTouchHelper.RIGHT -> {
                    launch {
                        adapter.onSwipedRight(viewHolder)
                        delay(SWIPE_DURATION)
                        adapter.afterSwipeRight(viewHolder)
                    }
                }
                ItemTouchHelper.LEFT -> {
                    launch {
                        adapter.onSwipedLeft(viewHolder)
                        delay(SWIPE_DURATION)
                        adapter.afterSwipeLeft(viewHolder)
                    }
                }
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
            ItemTouchHelper.ACTION_STATE_DRAG -> drawOnMove(
                recyclerView,
                viewHolder.itemView,
                isCurrentlyActive,
                dY
            )
        }
    }

    private fun drawOnMove(
        recyclerView: RecyclerView,
        view: View,
        isCurrentlyActive: Boolean,
        dY: Float
    ) {
        if (isCurrentlyActive) {
            var originalElevation: Any? = view.getTag(androidx.recyclerview.R.id.item_touch_helper_previous_elevation)
            if (originalElevation == null) {
                originalElevation = ViewCompat.getElevation(view)
                val newElevation = 5f + findMaxElevation(recyclerView, view)
                ViewCompat.setElevation(view, newElevation)
                view.setTag(androidx.recyclerview.R.id.item_touch_helper_previous_elevation, originalElevation)
            }
        }
        view.translationY = dY
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

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        getDefaultUIUtil().clearView(viewHolder.itemView.findViewById(R.id.content))
        getDefaultUIUtil().clearView(viewHolder.itemView)
        adapter.onClearView()
    }

    override fun isLongPressDragEnabled(): Boolean = false
}