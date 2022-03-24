package com.apm.trackify.ui.playlist.details.model

data class Track(
    val id: Any,
    val imageUri: String,
    val name: String,
    val artists: String,
    val explicit: Boolean,
    val duration: Int,
    val previewUrl: String
)