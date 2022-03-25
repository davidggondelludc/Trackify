package com.apm.trackify.ui.playlists.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.TrackDragItemBinding
import com.apm.trackify.ui.playlists.details.model.Track
import com.apm.trackify.ui.playlists.details.model.diff.TrackDiffUtil
import com.apm.trackify.ui.playlists.details.view.TrackViewHolder

class TrackAdapter(private val itemTouchHelper: ItemTouchHelper) :
    ListAdapter<Track, TrackViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TrackDragItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding, itemTouchHelper)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}