package com.apm.trackify.ui.playlists.details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackFooterItemBinding
import com.apm.trackify.databinding.PlaylistsTrackItemBinding
import com.apm.trackify.provider.model.diff.TrackItemDiffUtil
import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.details.view.holder.FooterViewHolder
import com.apm.trackify.ui.playlists.details.view.holder.TrackViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.snackbar
import com.apm.trackify.util.extension.toggleVisibility

class TrackAdapter(
    private val mediaService: MediaServiceLifecycle
) : PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(TrackItemDiffUtil()) {

    override fun getItemViewType(position: Int): Int =
        when (peek(position)) {
            is UiModel.TrackItem -> R.layout.playlists_track_item
            is UiModel.Footer -> R.layout.playlists_track_footer_item
            null -> throw IllegalStateException("Unknown view")
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.playlists_track_item -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = PlaylistsTrackItemBinding.inflate(inflater, parent, false)

                TrackViewHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = PlaylistsTrackFooterItemBinding.inflate(inflater, parent, false)

                FooterViewHolder(binding)
            }
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TrackViewHolder -> {
                val track = getItem(position) as UiModel.TrackItem
                holder.coverImageView.loadFromURI(track.imageUri, R.drawable.placeholder_track)
                holder.nameTextView.text = track.name
                holder.artistsTextView.text = track.artists
                holder.explicitImageView.toggleVisibility(track.explicit, true)

                holder.itemView.setOnClickListener {
                    if (track.previewUrl == null) {
                        mediaService.stop()
                        it.snackbar(R.string.preview_url)
                    } else {
                        mediaService.play(track.previewUrl)
                    }
                }
            }
            is FooterViewHolder -> {
                val footer = getItem(position) as UiModel.Footer

                holder.descriptionTextView.text = footer.description
            }
        }
    }
}