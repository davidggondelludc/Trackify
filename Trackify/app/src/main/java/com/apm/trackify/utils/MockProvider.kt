package com.apm.trackify.utils

import com.apm.trackify.ui.playlists.navigation.tracks.model.Track
import com.apm.trackify.ui.playlists.model.Playlist

// TODO: change placeholders for retrieved data
object MockProvider {

    val playlists = List(20) {
        Playlist(
            it,
            "$it",
            "playlist $it",
            it,
            "User $it"
        )
    }

    val playlist = Playlist(
        1,
        "1",
        "playlist",
        20,
        "User"
    )

    val tracks = List(61) {
        Track(
            it,
            "$it",
            "track $it",
            "artists",
            true,
            60000,
            ""
        )
    }
}