package com.apm.trackify.provider.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.provider.model.domain.UiModel

class TrackItemDiffUtil : DiffUtil.ItemCallback<UiModel>() {

    override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
        val isSameTrackItem = oldItem is UiModel.TrackItem
                && newItem is UiModel.TrackItem
                && oldItem.id == newItem.id

        val isSameFooter = oldItem is UiModel.Footer
                && newItem is UiModel.Footer
                && oldItem.description == newItem.description

        return isSameTrackItem || isSameFooter
    }

    override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
        oldItem == newItem
}