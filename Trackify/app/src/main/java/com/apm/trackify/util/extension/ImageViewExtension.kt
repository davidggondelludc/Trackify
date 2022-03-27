package com.apm.trackify.util.extension

import android.widget.ImageView
import com.apm.trackify.util.CoverUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority

fun ImageView.loadFromURI(uri: String, placeholder: Int) {
    Glide.with(context)
        .load(uri)
        .priority(Priority.IMMEDIATE)
        .placeholder(CoverUtil.getDrawable(context, placeholder, uri.hashCode()))
        .error(CoverUtil.getDrawable(context, placeholder, uri.hashCode()))
        .into(this)
}