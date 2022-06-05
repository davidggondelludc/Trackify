package com.apm.trackify.provider.model

import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.model.domain.UserItem
import com.google.android.gms.maps.model.LatLng

// TODO: change placeholders for retrieved data
object MockProvider {

    val routes = List(20) {
        RouteItem(
            "$it",
            "Route $it",
            "url $it",
            1.0,
            1.0,
            listOf(LatLng(1.0, 1.0)),
            "creator"
        )
    }

    val users = List(20) {
        UserItem(
            "Usuario $it",
            listOf("User $it"),
            listOf("User $it"),
            5
        )
    }

    val usersMutable = MutableList(20) {
        UserItem(
            "Usuario $it",
            listOf("User $it"),
            listOf("User $it"),
            5
        )
    }

    val playlists = List(20) {
        PlaylistItem(
            "$it playlist",
            "https://open.spotify.com/playlist/5Ka9ePddRWRGLKYJ7v6gAE?si=daeb8cd4d95c41af",
            "https://mosaic.scdn.co/640/ab67616d0000b2733a2d2b9621ef645380c63c16ab67616d0000b273b3ed0d541b9c8508d3b6f729ab67616d0000b273ba4c798ab356b8adb64bb2fcab67616d0000b273dbe9c4609ec382dcc0391e62",
            "Playlist $it",
            "User $it",
            it,
            true
        )
    }

    val playlist = PlaylistItem(
        "1 playlist",
        "https://open.spotify.com/playlist/5Ka9ePddRWRGLKYJ7v6gAE?si=daeb8cd4d95c41af",
        "https://mosaic.scdn.co/640/ab67616d0000b2733a2d2b9621ef645380c63c16ab67616d0000b273b3ed0d541b9c8508d3b6f729ab67616d0000b273ba4c798ab356b8adb64bb2fcab67616d0000b273dbe9c4609ec382dcc0391e62",
        "Playlist",
        "User",
        20,
        true
    )
}