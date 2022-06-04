package com.apm.trackify.provider.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaylistItem(
    val id: String,
    val playlistUri: String,
    val imageUri: String,
    val name: String,
    val owner: String,
    val totalTracks: Int,
    val public: Boolean
) : Parcelable