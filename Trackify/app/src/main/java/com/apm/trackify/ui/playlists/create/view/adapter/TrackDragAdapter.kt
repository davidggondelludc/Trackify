package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackDragItemBinding
import com.apm.trackify.provider.model.diff.TrackItemDiffUtil
import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.view.holder.TrackDragViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.snackbar
import com.apm.trackify.util.extension.toggleVisibility

class TrackDragAdapter(
    private val itemTouchHelper: ItemTouchHelper,
    private val mediaService: MediaServiceLifecycle
) : ListAdapter<UiModel, TrackDragViewHolder>(TrackItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackDragViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackDragItemBinding.inflate(inflater, parent, false)

        return TrackDragViewHolder(binding, itemTouchHelper)
    }

    override fun onBindViewHolder(holder: TrackDragViewHolder, position: Int) {
        val track = getItem(position) as UiModel.TrackItem

        holder.coverImageView.loadFromURI(track.imageUri, R.drawable.placeholder_track)
        holder.nameTextView.text = track.name
        holder.explicitImageView.toggleVisibility(track.explicit, true)
        holder.artistsTextView.text = track.artists

        holder.itemView.setOnClickListener {
            if (track.previewUrl == null) {
                mediaService.stop()
                it.snackbar(R.string.preview_url)
            } else {
                mediaService.play(track.previewUrl)
            }
        }
    }
}