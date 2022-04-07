package com.apm.trackify.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.model.domain.Playlist

class PlaylistDiffUtil : DiffUtil.ItemCallback<Playlist>() {

    override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
        oldItem == newItem
}