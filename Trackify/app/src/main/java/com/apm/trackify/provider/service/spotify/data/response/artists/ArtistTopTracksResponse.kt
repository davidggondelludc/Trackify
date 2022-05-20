package com.apm.trackify.provider.service.spotify.data.response.artists

import com.apm.trackify.provider.service.spotify.data.Track

data class ArtistTopTracksResponse(
    val tracks: List<Track>
)