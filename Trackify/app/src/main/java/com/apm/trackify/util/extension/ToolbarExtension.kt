package com.apm.trackify.util.extension

import android.view.View
import androidx.appcompat.widget.Toolbar

fun Toolbar.toggleVisibility(visible: Boolean, gone: Boolean) {
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