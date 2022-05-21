package com.apm.trackify.util.extension

import android.view.View
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.toggleVisibility(visible: Boolean, gone: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        if (gone) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.INVISIBLE
        }
    }
}