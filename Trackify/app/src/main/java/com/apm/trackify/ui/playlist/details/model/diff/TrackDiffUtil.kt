package com.apm.trackify.ui.playlist.details.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.ui.playlist.details.model.Track

class TrackDiffUtil : DiffUtil.ItemCallback<Track>() {

    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
        oldItem == newItem
}