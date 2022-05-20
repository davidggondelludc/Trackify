package com.apm.trackify.ui.playlists.create.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackAddItemBinding
import com.apm.trackify.provider.model.diff.TrackItemDiffUtil
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.search.view.holder.TrackAddViewHolder
import com.apm.trackify.ui.playlists.create.view.model.PlaylistCreateViewModel
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.snackbar
import com.apm.trackify.util.extension.toggleVisibility

class TrackAddAdapter(
    private val viewModel: PlaylistCreateViewModel,
    private val mediaService: MediaServiceLifecycle
) : ListAdapter<TrackItem, TrackAddViewHolder>(TrackItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackAddViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackAddItemBinding.inflate(inflater, parent, false)

        return TrackAddViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackAddViewHolder, position: Int) {
        val track = getItem(position)

        holder.coverImageView.loadFromURI(track.imageUri, R.drawable.placeholder_track)
        holder.nameTextView.text = track.name
        holder.explicitImageView.toggleVisibility(track.explicit, true)
        holder.artistsTextView.text = track.artists

        holder.itemView.setOnClickListener {
            if (track.previewUrl == null) {
                mediaService.stop()
                it.snackbar(R.string.preview_url)
            } else {
                mediaService.play(track.previewUrl, position)
            }
        }

        holder.addButton.setOnClickListener {
            viewModel.add(track)
        }
    }
}