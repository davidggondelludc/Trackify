package com.apm.trackify.service.spotify.domain

import com.google.gson.annotations.SerializedName

data class PlaylistsResponse(
    @SerializedName("items") var items: List<PlaylistSpotify>
)

data class PlaylistSpotify(
    @SerializedName("id") val id: String,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("images") val images: List<PlaylistImages>,
    @SerializedName("name") val name: String,
    @SerializedName("tracks") val tracks: PlaylistTracks,
    @SerializedName("owner") val owner: PlaylistOwner
)

data class ExternalUrls(
    @SerializedName("spotify") val spotify: String
)

data class PlaylistImages(
    @SerializedName("width") val imageWidth: Int,
    @SerializedName("url") val imageUrl: String
)

data class PlaylistTracks(
    @SerializedName("href") val tracksUrl: String,
    @SerializedName("total") val totalTracks: Int
)

data class PlaylistOwner(
    @SerializedName("display_name") val ownerName: String
)