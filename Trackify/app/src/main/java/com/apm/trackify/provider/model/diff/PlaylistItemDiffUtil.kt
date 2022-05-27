package com.apm.trackify.provider.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.provider.model.domain.PlaylistItem

class PlaylistItemDiffUtil : DiffUtil.ItemCallback<PlaylistItem>() {

    override fun areItemsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean =
        oldItem == newItem
}