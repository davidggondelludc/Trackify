package com.apm.trackify.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.model.domain.TrackItem

class TrackItemDiffUtil : DiffUtil.ItemCallback<TrackItem>() {

    override fun areItemsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean =
        oldItem == newItem
}