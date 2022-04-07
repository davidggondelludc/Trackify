package com.apm.trackify.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val name: String,
    val sharedPlaylists: Int
) : Parcelable