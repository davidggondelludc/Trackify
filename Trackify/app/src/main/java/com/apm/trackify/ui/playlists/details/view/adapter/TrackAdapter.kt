package com.apm.trackify.ui.playlists.details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackItemBinding
import com.apm.trackify.model.diff.TrackDiffUtil
import com.apm.trackify.model.domain.Track
import com.apm.trackify.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.details.view.holder.TrackViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.toast
import com.apm.trackify.util.extension.toggleVisibility

class TrackAdapter(private val mediaService: MediaServiceLifecycle) :
    ListAdapter<Track, TrackViewHolder>(TrackDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackItemBinding.inflate(inflater, parent, false)

        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = getItem(position)

        holder.coverShapeableImageView.loadFromURI(track.imageUri, R.drawable.placeholder_track)
        holder.nameTextView.text = track.name
        holder.artistsTextView.text = track.artists
        holder.explicitImageView.toggleVisibility(track.explicit, true)

        holder.itemView.setOnClickListener {
            mediaService.play(track.previewUrl)
        }

        holder.moreButton.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, holder.moreButton)
            popupMenu.menuInflater.inflate(R.menu.popup_track, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.play -> {
                        holder.itemView.context.toast("PLAY")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}