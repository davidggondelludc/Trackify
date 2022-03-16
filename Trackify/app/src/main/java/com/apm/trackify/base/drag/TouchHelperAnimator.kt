package com.apm.trackify.base.drag

import android.graphics.Color
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.isVisible
import com.apm.trackify.R
import com.apm.trackify.base.extensions.toggleVisibility
import kotlin.math.hypot

internal enum class State {
    IDLE, SWIPE_LEFT, SWIPE_RIGHT, CIRCULAR_REVEAL
}

internal class TouchHelperAnimator(
    view: View
) {

    private var state = State.IDLE
    private val background: View = view.findViewById(R.id.background)
    private val leftIcon: ImageView = view.findViewById(R.id.leftIcon)
    private val rightIcon: ImageView = view.findViewById(R.id.rightIcon)

    fun setAnimationIdle() {
        state = State.IDLE
    }

    fun initializeSwipe(dX: Float) {
        if (dX > 0) {
            if (state == State.SWIPE_RIGHT) {
                return
            }
            state = State.SWIPE_RIGHT
        }
        if (dX < 0) {
            if (state == State.SWIPE_LEFT) {
                return
            }
            state = State.SWIPE_LEFT
        }

        background.setBackgroundColor(0xff_bdbdbd.toInt())
        leftIcon.setColorFilter(0xff_60_63_67.toInt())
        leftIcon.toggleVisibility(dX > 0, false)
        rightIcon.setColorFilter(0xff_60_63_67.toInt())
        rightIcon.toggleVisibility(dX < 0, false)
    }

    fun drawCircularReveal(dx: Float) {
        if (state == State.CIRCULAR_REVEAL) {
            return
        }
        state = State.CIRCULAR_REVEAL

        background.isVisible = true
        background.setBackgroundColor(if (dx > 0) 0xff_cf_17_21.toInt() else 0xff_36_48_54.toInt())

        val mainIcon = if (dx > 0f) leftIcon else rightIcon
        mainIcon.setColorFilter(Color.WHITE)

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
        anim.duration = 400
        anim.interpolator = AccelerateInterpolator()
        anim.start()

        var animator = mainIcon.animate()
        animator.duration = 200
        animator.interpolator = DecelerateInterpolator()
        animator.scaleX(1.2f)
        animator.scaleY(1.2f)
        animator.withEndAction {
            animator = mainIcon.animate()
            animator.duration = 200
            animator.interpolator = BounceInterpolator()
            animator.scaleX(1f)
            animator.scaleY(1f)
        }
    }
}