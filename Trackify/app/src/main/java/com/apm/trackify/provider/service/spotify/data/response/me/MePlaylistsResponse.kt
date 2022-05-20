package com.apm.trackify.provider.service.spotify.data.response.me

import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.service.spotify.data.Playlist

data class MePlaylistsResponse(
    val items: List<Playlist>
) {

    fun toPlaylistItems(): List<PlaylistItem> {
        val playlists = mutableListOf<PlaylistItem>()
        items.forEach {
            playlists.add(it.toPlaylistItem())
        }
        return playlists
    }
}