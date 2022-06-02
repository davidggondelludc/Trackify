package com.apm.trackify.provider.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.provider.model.domain.PlaylistRequestItem

class PlaylistRequestItemDiffUtil : DiffUtil.ItemCallback<PlaylistRequestItem>() {

    override fun areItemsTheSame(
        oldItem: PlaylistRequestItem,
        newItem: PlaylistRequestItem
    ): Boolean =
        oldItem.name == newItem.name && oldItem.duration == newItem.duration

    override fun areContentsTheSame(
        oldItem: PlaylistRequestItem,
        newItem: PlaylistRequestItem
    ): Boolean =
        oldItem.name == newItem.name && oldItem.duration == newItem.duration
}