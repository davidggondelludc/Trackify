package com.apm.trackify.util.extension

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.StringRes
import es.dmoral.toasty.Toasty

fun Context.isDarkMode(): Boolean =
    resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun Context.toastError(message: CharSequence): Toast =
    Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

fun Context.toastError(@StringRes resId: Int): Toast =
    Toasty.error(this, resId, Toast.LENGTH_SHORT, true).apply {
        show()
    }