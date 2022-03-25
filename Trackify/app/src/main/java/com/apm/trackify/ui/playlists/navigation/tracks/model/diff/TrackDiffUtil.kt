package com.apm.trackify.ui.playlists.navigation.tracks.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.ui.playlists.navigation.tracks.model.Track

class TrackDiffUtil : DiffUtil.ItemCallback<Track>() {

    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
        oldItem == newItem
}