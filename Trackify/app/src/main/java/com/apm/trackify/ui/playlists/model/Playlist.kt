package com.apm.trackify.ui.playlists.model

data class Playlist(
    val id: Any,
    val imageUri: String,
    val name: String,
    val totalTracks: Int,
    val owner: String
)