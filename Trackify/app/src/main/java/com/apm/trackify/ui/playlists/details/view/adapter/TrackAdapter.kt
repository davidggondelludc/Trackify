package com.apm.trackify.ui.playlists.details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackItemBinding
import com.apm.trackify.provider.model.diff.TrackItemDiffUtil
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.details.view.holder.TrackViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.snackbar
import com.apm.trackify.util.extension.toggleVisibility

class TrackAdapter(
    private val mediaService: MediaServiceLifecycle
) : ListAdapter<TrackItem, TrackViewHolder>(TrackItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = getItem(position)

        holder.coverImageView.loadFromURI(track.imageUri, R.drawable.placeholder_track)
        holder.nameTextView.text = track.name
        holder.artistsTextView.text = track.artists
        holder.explicitImageView.toggleVisibility(track.explicit, true)

        holder.itemView.setOnClickListener {
            if (track.previewUrl == null) {
                mediaService.stop()
                it.snackbar(R.string.preview_url)
            } else {
                mediaService.play(track.previewUrl, position)
            }
        }
    }
}