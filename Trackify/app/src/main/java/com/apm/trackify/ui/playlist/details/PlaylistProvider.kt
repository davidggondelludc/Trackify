package com.apm.trackify.ui.playlist.details

import com.apm.trackify.ui.playlist.details.model.Track
import com.apm.trackify.ui.playlist.model.Playlist

object PlaylistProvider {

    val playlist = Playlist(1, "1", "playlist")

    val tracks = List(20) { Track(it, "$it", "track $it", "artists", true, 1000) }
}