package com.apm.trackify.ui.playlist.details.adapter.drag

import android.graphics.Color
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import androidx.core.view.isVisible
import com.apm.trackify.extensions.toggleVisibility
import com.apm.trackify.databinding.SwipeableLayoutBinding
import kotlin.math.hypot

internal enum class State {
    IDLE, SWIPE_LEFT, SWIPE_RIGHT, CIRCULAR_REVEAL
}

class TouchHelperAnimator(
    view: View
) {

    private var state = State.IDLE
    private val background: View
    private val leftIcon: ImageButton
    private val rightIcon: ImageButton

    init {
        val binding = SwipeableLayoutBinding.bind(view)

        background = binding.background
        leftIcon = binding.leftIcon
        rightIcon = binding.rightIcon
    }

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
        leftIcon.apply {
            setColorFilter(0xff_60_63_67.toInt())
            toggleVisibility(dX > 0, false)
        }
        rightIcon.apply {
            setColorFilter(0xff_60_63_67.toInt())
            toggleVisibility(dX < 0, false)
        }
    }

    fun drawCircularReveal(dx: Float) {
        if (state == State.CIRCULAR_REVEAL) {
            return
        }
        state = State.CIRCULAR_REVEAL

        background.isVisible = true
        background.setBackgroundColor(if (dx > 0) 0xff_cf_17_21.toInt() else 0xff_36_48_54.toInt())

        val mainIcon = if (dx > 0f) leftIcon else rightIcon
        mainIcon.apply {
            setColorFilter(Color.WHITE)
            animate().apply {
                duration = 200
                interpolator = DecelerateInterpolator()
                scaleX(1.2f)
                scaleY(1.2f)
                withEndAction {
                    mainIcon.animate().apply {
                        duration = 200
                        interpolator = BounceInterpolator()
                        scaleX(1f)
                        scaleY(1f)
                    }
                }
            }
        }

        val cx = mainIcon.x + mainIcon.width / 2
        val cy = mainIcon.y + mainIcon.height / 2
        val endRadius = hypot(background.width.toDouble(), background.height.toDouble()).toFloat()

        ViewAnimationUtils.createCircularReveal(
            background,
            cx.toInt(),
            cy.toInt(),
            0f,
            endRadius
        ).apply {
            duration = 400
            interpolator = AccelerateInterpolator()
            start()
        }
    }
}