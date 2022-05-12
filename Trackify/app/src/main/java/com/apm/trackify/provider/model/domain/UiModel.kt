package com.apm.trackify.provider.model.domain

import com.apm.trackify.provider.service.spotify.data.Track

sealed class UiModel {

    data class TrackItem(
        val id: String,
        val imageUri: String,
        val name: String,
        val artists: String,
        val explicit: Boolean,
        val duration: Int,
        val previewUrl: String?
    ) : UiModel() {

        constructor(track: Track) : this(
            track.id,
            track.album.images.last().url,
            track.name,
            track.artists.joinToString(", ") { it.name },
            track.explicit,
            track.duration_ms,
            track.preview_url
        )
    }

    data class Footer(val description: String) : UiModel()
}