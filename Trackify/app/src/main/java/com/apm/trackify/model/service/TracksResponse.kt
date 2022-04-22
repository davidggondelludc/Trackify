package com.apm.trackify.model.service

import com.google.gson.annotations.SerializedName

data class TracksResponse(
    @SerializedName("items") var items: List<TrackSpotify>
)

data class TrackSpotify(
    @SerializedName("id") var id: String,
    @SerializedName("album") val album: AlbumSpotify,
    @SerializedName("name") val name: String,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("artists") val artists: MutableList<ArtistSpotify>,
    @SerializedName("duration_ms") val duration: Int,
    @SerializedName("preview_url") val previewUrl: String
)

data class AlbumSpotify(
    @SerializedName("images") val images: List<TrackImages>
)

data class ArtistSpotify(
    @SerializedName("name") val name: String
)

data class TrackImages(
    @SerializedName("width") val imageWidth: Int,
    @SerializedName("url") val imageUrl: String
)
