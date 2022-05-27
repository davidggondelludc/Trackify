package com.apm.trackify.provider.service.spotify.data.response.artists

import com.apm.trackify.provider.service.spotify.data.Artist

data class ArtistRelatedArtistsResponse(
    val artists: List<Artist>
)