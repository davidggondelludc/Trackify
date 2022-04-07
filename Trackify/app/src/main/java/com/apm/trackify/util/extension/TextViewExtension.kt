package com.apm.trackify.util.extension

import android.widget.TextView

fun TextView.getQuantityString(id: Int, quantity: Int, formatArgs: Any) {
    text = context.resources.getQuantityString(id, quantity, formatArgs)
}