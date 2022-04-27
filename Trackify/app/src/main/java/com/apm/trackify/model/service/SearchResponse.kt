package com.apm.trackify.model.service

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("tracks") var tracks: TracksContent
)

data class TracksContent(
    @SerializedName("items") var items: List<TrackSpotify>
)