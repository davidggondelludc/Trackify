package com.apm.trackify.base.adapter

import android.view.View

interface BaseModel {
    val type: Int

    fun bind(view: View, position: Int)
}