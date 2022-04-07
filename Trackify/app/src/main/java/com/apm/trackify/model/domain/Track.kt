package com.apm.trackify.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track(
    val id: String,
    val imageUri: String,
    val name: String,
    val artists: String,
    val explicit: Boolean,
    val duration: Int,
    val previewUrl: String
) : Parcelable