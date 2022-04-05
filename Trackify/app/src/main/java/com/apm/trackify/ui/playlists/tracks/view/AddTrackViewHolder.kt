package com.apm.trackify.ui.playlists.tracks.view

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackAddItemBinding
import com.apm.trackify.model.domain.Track
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.toast
import com.apm.trackify.util.extension.toggleVisibility

class AddTrackViewHolder(private val binding: PlaylistsTrackAddItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(track: Track) {
        // TODO: replace toast with track playback
        binding.root.setOnClickListener { it.context.toast("PLAYBACK ${track.name}") }
        binding.cover.loadFromURI(track.imageUri, R.drawable.placeholder_musical_note)
        binding.name.text = track.name
        binding.artists.text = track.artists
        binding.explicit.toggleVisibility(track.explicit, true)
    }
}