package com.apm.trackify.model.service

import com.apm.trackify.model.domain.Track

class TracksMapper {
    fun mapTracks(tracksResponse: TracksResponse): List<Track> {
        val items = tracksResponse.items
        val tracksList = mutableListOf<Track>()
        for (item in items) {
            tracksList.add(
                Track(
                    item.track.id,
                    item.track.album.images.first().imageUrl,
                    item.track.name,
                    item.track.artists.joinToString(", ") { it.name },
                    item.track.explicit,
                    item.track.duration,
                    item.track.previewUrl
                )
            )
        }
        return tracksList
    }
}