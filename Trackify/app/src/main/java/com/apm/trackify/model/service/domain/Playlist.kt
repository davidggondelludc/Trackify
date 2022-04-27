package com.apm.trackify.model.service.domain

import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.model.service.response.TracksResponse

data class Playlist(
    val external_urls: ExternalUrls,
    val id: String,
    val images: List<Image>,
    val name: String,
    val owner: Owner,
    val tracks: TracksResponse
) {

    fun toPlaylistItem(): PlaylistItem {
        return PlaylistItem(
            id,
            external_urls.spotify,
            images.first().url,
            name,
            owner.display_name,
            tracks.total
        )
    }
}