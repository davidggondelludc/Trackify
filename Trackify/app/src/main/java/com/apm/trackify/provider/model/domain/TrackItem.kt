package com.apm.trackify.provider.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class TrackItem(
    val id: String,
    val uri: String,
    val imageUri: String,
    val name: String,
    val artists: String,
    val explicit: Boolean,
    val duration: Int,
    val previewUrl: String?,
    val addedAt: Date
) : Parcelable