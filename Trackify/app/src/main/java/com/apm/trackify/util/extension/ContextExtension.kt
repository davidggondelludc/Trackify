package com.apm.trackify.util.extension

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.isDarkMode(): Boolean =
    resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun Context.toast(message: CharSequence): Toast =
    Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

fun Context.toast(@StringRes resId: Int): Toast =
    Toast
        .makeText(this, resId, Toast.LENGTH_SHORT)
        .apply {
            show()
        }