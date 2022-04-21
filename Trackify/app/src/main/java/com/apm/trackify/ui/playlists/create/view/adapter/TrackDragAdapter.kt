package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackDragItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.view.holder.TrackDragViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.toggleVisibility

class TrackDragAdapter(
    private val itemTouchHelper: ItemTouchHelper,
    private val mediaService: MediaServiceLifecycle
) : ListAdapter<Track, TrackDragViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackDragViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackDragItemBinding.inflate(inflater, parent, false)

        return TrackDragViewHolder(binding, itemTouchHelper)
    }

    override fun onBindViewHolder(holder: TrackDragViewHolder, position: Int) {
        val track = getItem(position)

        holder.coverImageView.loadFromURI(track.imageUri, R.drawable.placeholder_track)
        holder.nameTextView.text = track.name
        holder.explicitImageView.toggleVisibility(track.explicit, true)
        holder.artistsTextView.text = track.artists

        holder.itemView.setOnClickListener {
            mediaService.play(track.previewUrl)
        }
    }
}