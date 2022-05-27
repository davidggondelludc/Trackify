package com.apm.trackify.provider.service.spotify.data.response.me

import com.apm.trackify.provider.service.spotify.data.Artist

data class MeTopArtistsResponse(
    val items: List<Artist>
)