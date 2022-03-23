package com.apm.trackify.extensions

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }