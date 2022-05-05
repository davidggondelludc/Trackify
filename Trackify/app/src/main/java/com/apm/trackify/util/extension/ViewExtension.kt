package com.apm.trackify.util.extension

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.toggleVisibility(visible: Boolean, gone: Boolean) {
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

fun View.snackbar(@StringRes resId: Int): Snackbar =
    Snackbar
        .make(this, resId, BaseTransientBottomBar.LENGTH_LONG)
        .apply {
            show()
        }