package com.apm.trackify.ui.playlists.tracks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistTrackDragItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.ui.playlists.tracks.view.TrackViewHolder

class TrackAdapter(private val itemTouchHelper: ItemTouchHelper) :
    ListAdapter<Track, TrackViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistTrackDragItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding, itemTouchHelper)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}