package com.apm.trackify.service.spotify.model.mapper

import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.service.spotify.model.domain.PlaylistsResponse

object PlaylistMapper {

    fun toPlaylist(playlistsResponse: PlaylistsResponse): List<Playlist> {
        val items = playlistsResponse.items
        val playlistList = mutableListOf<Playlist>()
        for (item in items) {
            val id = item.id
            val name = item.name
            val playlistUri = item.externalUrls.spotify
            val totalTracks = item.tracks.totalTracks
            val owner = item.owner.ownerName
            val images = item.images
            val firstImg = images.firstOrNull { it.imageWidth == 640 }
            val imgUrl = firstImg?.imageUrl ?: images[0].imageUrl
            playlistList.add(Playlist(id, playlistUri, imgUrl, name, totalTracks, owner))
        }

        return playlistList
    }
}