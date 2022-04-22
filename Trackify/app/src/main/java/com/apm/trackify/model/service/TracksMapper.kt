package com.apm.trackify.model.service

import com.apm.trackify.model.domain.Track

class TracksMapper {
    fun mapTracks(tracksResponse: TracksResponse): List<Track> {
        val items = tracksResponse.items
        val tracksList = mutableListOf<Track>()
        for (item in items) {
            val id = item.id
            val images = item.album.images
            val firstImg = images.firstOrNull { it.imageWidth == 640 }
            val imgUrl = firstImg?.imageUrl ?: images[0].imageUrl
            val name = item.name
            val explicit = item.explicit
            val duration = item.duration
            val previewUrl = item.previewUrl
            val artistsList = item.artists
            var artists = artistsList.removeFirst().name
            for (artist in artistsList) {
                artists += ", " + artist.name
            }
            tracksList.add(Track(id, imgUrl, name, artists, explicit, duration, previewUrl))
        }
        return tracksList
    }
}