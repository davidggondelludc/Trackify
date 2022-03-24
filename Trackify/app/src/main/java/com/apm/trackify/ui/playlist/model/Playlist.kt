package com.apm.trackify.ui.playlist.model

import com.apm.trackify.ui.playlist.details.model.Track

data class Playlist(
    val id: Any,
    val imageUri: String,
    val name: String,
    val tracks: List<Track>
)