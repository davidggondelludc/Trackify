package com.apm.trackify.model.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Playlist(
    val id: String,
    val playlistUri: String,
    val imageUri: String,
    val name: String,
    val totalTracks: Int,
    val owner: String
) : Parcelable