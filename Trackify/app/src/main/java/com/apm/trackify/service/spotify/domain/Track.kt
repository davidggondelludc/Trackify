package com.apm.trackify.service.spotify.domain

import com.apm.trackify.model.domain.TrackItem

data class Track(
    val album: Album,
    val artists: List<Artist>,
    val duration_ms: Int,
    val explicit: Boolean,
    val id: String,
    val name: String,
    val preview_url: String?
) {

    fun toTrackItem(): TrackItem {
        return TrackItem(
            id,
            album.images.last().url,
            name,
            artists.joinToString(", ") { it.name },
            explicit,
            duration_ms,
            preview_url
        )
    }
}