package com.apm.trackify.base.extensions

import android.widget.ImageView
import com.apm.trackify.base.utils.CoverUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority

fun ImageView.loadFromURI(uri: String, placeholder: Int) {
    Glide.with(context)
        .load(uri)
        .priority(Priority.IMMEDIATE)
        .placeholder(CoverUtils.getDrawable(context, placeholder, uri.hashCode()))
        .error(CoverUtils.getDrawable(context, placeholder, uri.hashCode()))
        .into(this)
}