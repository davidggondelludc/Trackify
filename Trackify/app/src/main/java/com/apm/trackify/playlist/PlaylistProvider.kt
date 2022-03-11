package com.apm.trackify.playlist

import com.apm.trackify.playlist.model.DisplayableFooter
import com.apm.trackify.playlist.model.DisplayableHeader
import com.apm.trackify.playlist.model.DisplayableTrack

class PlaylistProvider {
    companion object {
        val playlist = listOf(
            DisplayableHeader(title="album"),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = false),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableTrack(title = "track", subtitle = "artists", explicit = true),
            DisplayableFooter(title = "3 Tracks · 9 Minutes")
        )
    }
}