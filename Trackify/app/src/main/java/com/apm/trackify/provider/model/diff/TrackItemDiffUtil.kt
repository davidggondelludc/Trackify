package com.apm.trackify.provider.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.provider.model.domain.TrackItem

class TrackItemDiffUtil : DiffUtil.ItemCallback<TrackItem>() {

    override fun areItemsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean =
        oldItem.id == newItem.id && oldItem.addedAt == newItem.addedAt

    override fun areContentsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean =
        oldItem == newItem
}