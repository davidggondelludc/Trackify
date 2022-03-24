package com.apm.trackify.ui.playlist.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistsDetailsTrackItemBinding
import com.apm.trackify.ui.playlist.details.model.Track
import com.apm.trackify.ui.playlist.details.view.TrackViewHolder

class TrackAdapter(private val itemTouchHelper: ItemTouchHelper) :
    ListAdapter<Track, TrackViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsDetailsTrackItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding, itemTouchHelper)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TrackDiffUtil : DiffUtil.ItemCallback<Track>() {

        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem == newItem
    }
}