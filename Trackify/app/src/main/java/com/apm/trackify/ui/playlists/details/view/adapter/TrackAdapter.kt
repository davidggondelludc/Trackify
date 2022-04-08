package com.apm.trackify.ui.playlists.details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.apm.trackify.databinding.PlaylistsTrackDragItemBinding
import com.apm.trackify.databinding.PlaylistsTrackItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.ui.playlists.details.view.holder.TrackViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class TrackAdapter : DelegateAdapter<Track, TrackViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding)
    }
}