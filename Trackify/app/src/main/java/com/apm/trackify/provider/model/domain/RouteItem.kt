package com.apm.trackify.provider.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteItem(
    val id: String,
    val title: String,
    val playlistUrl: String,
    val coordinates: String,
    val creator: String
) : Parcelable