package com.apm.trackify.service.spotify.domain.response

import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.service.spotify.domain.Playlist

data class PlaylistsResponse(
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