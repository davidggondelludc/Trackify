package com.apm.trackify.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Route(
    val id: String,
    val title: String,
    val playlistUrl: String,
    val coordinates: String,
    val creator: String
) : Parcelable