package com.apm.trackify.provider.service.spotify.data

import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.service.spotify.data.response.playlists.PlaylistTracksResponse

data class Playlist(
    val external_urls: ExternalUrls,
    val id: String,
    val images: List<Image>,
    val name: String,
    val owner: Owner,
    val tracks: PlaylistTracksResponse
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