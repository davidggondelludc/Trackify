package com.apm.trackify.utils.animation

import android.graphics.Color
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.isVisible
import com.apm.trackify.R
import kotlin.math.hypot

class SwipeAnimator() {

    private enum class State {
        IDLE, SWIPE, CIRCULAR_REVEAL
    }

    private var state = State.IDLE

    fun setIdle(view: View) {
        state = State.IDLE

        val background = view.findViewById<View>(R.id.background)
        val icon = view.findViewById<ImageView>(R.id.icon)

        background.isVisible = false
        icon.isVisible = false
    }

    fun initSwipe(view: View) {
        if (state == State.SWIPE) {
            return
        }
        state = State.SWIPE

        val background = view.findViewById<View>(R.id.background)
        val foreground = view.findViewById<View>(R.id.foreground)
        val icon = view.findViewById<ImageView>(R.id.icon)

        background.isVisible = true
        foreground.isVisible = false
        icon.isVisible = true
        icon.setColorFilter(0xFF_60_63_67.toInt())
    }


    fun drawCircularReveal(view: View) {
        if (state == State.CIRCULAR_REVEAL) {
            return
        }
        state = State.CIRCULAR_REVEAL

        val background = view.findViewById<View>(R.id.background)
        val foreground = view.findViewById<View>(R.id.foreground)
        val icon = view.findViewById<ImageView>(R.id.icon)

        background.isVisible = false
        foreground.isVisible = true
        icon.apply {
            setColorFilter(Color.WHITE)
            animate().apply {
                duration = 200
                interpolator = DecelerateInterpolator()
                scaleX(1.2f)
                scaleY(1.2f)
                withEndAction {
                    icon.animate().apply {
                        duration = 200
                        interpolator = BounceInterpolator()
                        scaleX(1f)
                        scaleY(1f)
                    }
                }
            }
        }

        val cx = icon.x + icon.width / 2
        val cy = icon.y + icon.height / 2
        val endRadius = hypot(
            foreground.width.toDouble(),
            foreground.height.toDouble()
        ).toFloat()

        ViewAnimationUtils.createCircularReveal(
            foreground,
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