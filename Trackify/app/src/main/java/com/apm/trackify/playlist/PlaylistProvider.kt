package com.apm.trackify.playlist

import com.apm.trackify.model.Track

class PlaylistProvider {
    companion object {
        val playlist = listOf<Track>(
            Track("title1", "subtitle", true),
            Track("title2", "subtitle", true),
            Track("title3", "subtitle", false),
            Track("title4", "subtitle", true),
            Track("title5", "subtitle", true),
            Track("title6", "subtitle", true),
            Track("title7", "subtitle", true),
            Track("title8", "subtitle", true),
            Track("title9", "subtitle", true),
            Track("title10", "subtitle", true),
            Track("title11", "subtitle", true),
            Track("title12", "subtitle", true),
            Track("title13", "subtitle", false),
            Track("title14", "subtitle", true),
            Track("title15", "subtitle", true),
            Track("title16", "subtitle", true)
        )
    }
}