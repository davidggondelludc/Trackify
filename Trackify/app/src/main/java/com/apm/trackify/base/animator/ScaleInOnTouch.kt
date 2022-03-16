package com.apm.trackify.base.animator

import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import com.apm.trackify.R

class ScaleInOnTouch : View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN ->
                AnimatorInflater.loadAnimator(view.context, R.animator.scale_in).apply {
                    setTarget(view)
                    start()
                }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
                AnimatorInflater.loadAnimator(view.context, R.animator.restore).apply {
                    setTarget(view)
                    start()
                }
        }
        return false
    }
}