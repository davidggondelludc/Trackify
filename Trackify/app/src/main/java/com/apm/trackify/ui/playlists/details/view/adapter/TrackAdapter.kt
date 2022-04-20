package com.apm.trackify.ui.playlists.details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.apm.trackify.databinding.PlaylistsTrackItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.details.view.holder.TrackViewHolder
import com.apm.trackify.util.base.DelegateAdapter
import com.apm.trackify.util.base.DelegateViewHolder

class TrackAdapter(private var mediaService: MediaServiceLifecycle) :
    DelegateAdapter<Track, TrackViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DelegateViewHolder<Track>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener {
            mediaService.play(getItem(position).previewUrl)
        }
    }
}