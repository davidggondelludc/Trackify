package com.apm.trackify.ui.playlist.details

import com.apm.trackify.ui.playlist.details.model.DisplayableFooter
import com.apm.trackify.ui.playlist.details.model.DisplayableHeader
import com.apm.trackify.ui.playlist.details.model.DisplayableTrack

class PlaylistProvider {
    // TODO: replace this class with real data
    companion object {
        val playlist = listOf(
            DisplayableHeader(
                imageURI = "",
                title = "album"
            ),
            DisplayableTrack(
                imageURI = "1",
                name = "track 1",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "2",
                name = "track 2",
                artists = "artists",
                explicit = false,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "3",
                name = "track 3",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "4",
                name = "track 4",
                artists = "artists",
                explicit = false,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "5",
                name = "track 5",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "6",
                name = "track 6",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "7",
                name = "track 7",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "8",
                name = "track 8",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "9",
                name = "track 9",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "10",
                name = "track 10",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "11",
                name = "track 11",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "12",
                name = "track 12",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "13",
                name = "track 13",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "14",
                name = "track 14",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "15",
                name = "track 15",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableTrack(
                imageURI = "16",
                name = "track 16",
                artists = "artists",
                explicit = true,
                duration = 1000
            ),
            DisplayableFooter(
                title = "3 Tracks · 9 Minutes"
            )
        )
    }
}