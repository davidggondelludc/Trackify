package com.apm.trackify.playlist

import com.apm.trackify.playlist.model.DisplayableFooter
import com.apm.trackify.playlist.model.DisplayableHeader
import com.apm.trackify.playlist.model.DisplayableTrack

class PlaylistProvider {
    // TODO: replace this class with real data
    companion object {
        val playlist = listOf(
            DisplayableHeader(title="album"),
            DisplayableTrack(title = "track 1", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 2", subtitle = "artists", explicit = false),
            DisplayableTrack(title = "track 3", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 4", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 5", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 6", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 7", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 8", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 9", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 10", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 11", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 12", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 13", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 14", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 15", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track 16", subtitle = "artists", explicit = true),
            DisplayableFooter(title = "3 Tracks · 9 Minutes")
        )
    }
}