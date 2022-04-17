package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.apm.trackify.databinding.PlaylistsTrackDragItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.ui.playlists.create.view.holder.TrackDragViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class TrackDragAdapter(private val itemTouchHelper: ItemTouchHelper) :
    DelegateAdapter<Track, TrackDragViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackDragViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackDragItemBinding.inflate(inflater, parent, false)

        return TrackDragViewHolder(binding, itemTouchHelper)
    }
}