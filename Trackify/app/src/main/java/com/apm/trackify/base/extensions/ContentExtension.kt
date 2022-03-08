package com.apm.trackify.base.extensions

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.apm.trackify.R

fun Context.themeAttributeToColor(themeAttributeId: Int, fallbackColor: Int = Color.WHITE): Int {
    val outValue = TypedValue()
    val theme = this.theme
    val resolved = theme.resolveAttribute(themeAttributeId, outValue, true)
    if (resolved) {
        return ContextCompat.getColor(this, outValue.resourceId)
    }
    return fallbackColor
}

inline fun Context.colorControlNormal(): Int {
    return themeAttributeToColor(com.google.android.material.R.attr.colorControlNormal)
}

inline fun Context.colorSwipeBackground(): Int {
    return themeAttributeToColor(R.attr.colorSwipeBackground)
}