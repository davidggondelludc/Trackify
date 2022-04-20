package com.apm.trackify.model

import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.model.domain.Route
import com.apm.trackify.model.domain.Track
import com.apm.trackify.model.domain.User

// TODO: change placeholders for retrieved data
object MockProvider {

    val routes = List(20) {
        Route(
            "1",
            "Playlist $it",
            3
        )
    }

    val users = List(20) {
        User(
            "$it",
            "User $it",
            5
        )
    }

    val playlists = List(20) {
        Playlist(
            "$it playlist",
            "https://mosaic.scdn.co/640/ab67616d0000b2733a2d2b9621ef645380c63c16ab67616d0000b273b3ed0d541b9c8508d3b6f729ab67616d0000b273ba4c798ab356b8adb64bb2fcab67616d0000b273dbe9c4609ec382dcc0391e62",
            "Playlist $it",
            it,
            "User $it"
        )
    }

    val playlist = Playlist(
        "1 playlist",
        "https://mosaic.scdn.co/640/ab67616d0000b2733a2d2b9621ef645380c63c16ab67616d0000b273b3ed0d541b9c8508d3b6f729ab67616d0000b273ba4c798ab356b8adb64bb2fcab67616d0000b273dbe9c4609ec382dcc0391e62",
        "Playlist",
        20,
        "User"
    )

    val tracks = List(61) {
        Track(
            "$it track",
            "https://mosaic.scdn.co/60/ab67616d0000b2733a2d2b9621ef645380c63c16ab67616d0000b273b3ed0d541b9c8508d3b6f729ab67616d0000b273ba4c798ab356b8adb64bb2fcab67616d0000b273dbe9c4609ec382dcc0391e62",
            "Track $it",
            "Artists",
            true,
            60000,
            "https://p.scdn.co/mp3-preview/4de2570644eeff4b10cef0770fddfc50e1e2b666?cid=774b29d4f13844c495f206cafdad9c86"
        )
    }
}