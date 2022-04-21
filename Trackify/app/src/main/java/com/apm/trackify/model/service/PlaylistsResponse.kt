package com.apm.trackify.model.prueba

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class PlaylistsResponse(
        @SerializedName("items") var items:List<PlaylistSpotify>
)

data class PlaylistSpotify(
        @SerializedName("id") val id: String,
        @SerializedName("href") val playlistUri: String,
        @SerializedName("images") val images: List<PlaylistImages>,
        @SerializedName("name") val name: String,
        @SerializedName("tracks") val tracks: PlaylistTracks,
        @SerializedName("owner") val owner: PlaylistOwner
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