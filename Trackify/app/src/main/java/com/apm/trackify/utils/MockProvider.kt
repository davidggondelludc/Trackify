package com.apm.trackify.utils

import com.apm.trackify.ui.playlist.details.model.Track
import com.apm.trackify.ui.playlist.model.Playlist

// TODO: change placeholder for retrieved data
object MockProvider {

    val playlist = Playlist(
        1,
        "1",
        "playlist",
        List(20) {
            Track(
                it,
                "$it",
                "track $it",
                "artists",
                true,
                1000,
                ""
            )
        })
}