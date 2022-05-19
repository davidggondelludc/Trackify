package com.apm.trackify.provider.service.spotify.data.response

import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.spotify.data.Track

data class TrackResponse(
    val track: Track
) {

    fun toTrackItem(): TrackItem =
        track.toTrackItem()
}