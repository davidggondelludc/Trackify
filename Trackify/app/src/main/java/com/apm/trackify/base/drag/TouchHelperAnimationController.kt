package com.apm.trackify.base.drag

import android.graphics.Color
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.base.extensions.toggleVisibility
import kotlin.math.hypot

internal class TouchHelperAnimationController {

    enum class State {
        IDLE, SWIPE_LEFT, SWIPE_RIGHT, CIRCULAR_REVEAL
    }

    private var state = State.IDLE

    fun setAnimationIdle() {
        state = State.IDLE
    }

    fun initializeSwipe(
        viewHolder: RecyclerView.ViewHolder,
        dx: Float
    ) {
        if (dx > 0) {
            if (state == State.SWIPE_RIGHT) {
                return
            }
            state = State.SWIPE_RIGHT
        }
        if (dx < 0) {
            if (state == State.SWIPE_LEFT) {
                return
            }
            state = State.SWIPE_LEFT
        }

        val left = viewHolder.itemView.findViewById<ImageView>(R.id.leftIcon)
        left.setColorFilter(0xff_606367.toInt())
        left.toggleVisibility(dx > 0, false)

        val right = viewHolder.itemView.findViewById<ImageView>(R.id.rightIcon)
        right.setColorFilter(0xff_606367.toInt())
        right.toggleVisibility(dx < 0, false)

        val background = viewHolder.itemView.findViewById<View>(R.id.background)
        background.setBackgroundColor(0xff_bdbdbd.toInt())
    }

    fun drawCircularReveal(
        viewHolder: RecyclerView.ViewHolder,
        dx: Float
    ) {
        if (state == State.CIRCULAR_REVEAL) {
            return
        }
        state = State.CIRCULAR_REVEAL

        val mainIcon =
            viewHolder.itemView.findViewById<ImageView>(if (dx > 0f) R.id.leftIcon else R.id.rightIcon)
        val background = viewHolder.itemView.findViewById<View>(R.id.background)

        if (mainIcon == null || background == null) {
            return
        }

        val cx = mainIcon.x + mainIcon.width / 2
        val cy = mainIcon.y + mainIcon.height / 2
        val endRadius = hypot(background.width.toDouble(), background.height.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(
            background,
            cx.toInt(),
            cy.toInt(),
            0f,
            endRadius
        )
        background.isVisible = true
        anim.duration = 400
        anim.interpolator = AccelerateInterpolator()
        anim.start()

        mainIcon.setColorFilter(Color.WHITE)
        background.setBackgroundColor(if (dx > 0) 0xff_cf1721.toInt() else 0xff_364854.toInt())

        mainIcon.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(200)
            .setInterpolator(DecelerateInterpolator())
            .withEndAction {
                mainIcon.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(200)
                    .setInterpolator(BounceInterpolator())
            }
    }
}