package com.apm.trackify.service.spotify.response

import com.apm.trackify.model.domain.TrackItem
import com.apm.trackify.service.spotify.domain.Track

data class TrackResponse(
    val track: Track
) {

    fun toTrackItem(): TrackItem = track.toTrackItem()
}