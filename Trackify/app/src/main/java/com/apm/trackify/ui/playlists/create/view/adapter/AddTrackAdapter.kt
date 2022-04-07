package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistsTrackAddItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.ui.playlists.tracks.view.AddTrackViewHolder

class AddTrackAdapter() : ListAdapter<Track, AddTrackViewHolder>(TrackDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackAddItemBinding.inflate(inflater, parent, false)

        return AddTrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddTrackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}