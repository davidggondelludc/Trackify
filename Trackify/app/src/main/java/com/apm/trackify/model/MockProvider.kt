package com.apm.trackify.model

import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.domain.Route
import com.apm.trackify.model.domain.Track
import com.apm.trackify.model.domain.User

// TODO: change placeholders for retrieved data
object MockProvider {

    val routes = List(20) {
        Route(
            1,
            "Playlist 1",
            3
        )
    }

    val users = List(20) {
        User(
            it,
            "user $it",
            5
        )
    }

    val playlists = List(20) {
        Playlist(
            it,
            "$it",
            "playlist $it",
            it,
            "user $it"
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