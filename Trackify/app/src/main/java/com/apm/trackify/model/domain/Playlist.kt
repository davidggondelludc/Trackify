package com.apm.trackify.model.domain

data class Playlist(
    val id: Any,
    val imageUri: String,
    val name: String,
    val totalTracks: Int,
    val owner: String
)