package com.apm.trackify.ui.playlists.create.search.view.holder

import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackAddItemBinding
import com.apm.trackify.model.domain.Track
import com.apm.trackify.util.base.DelegateViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.scaleOnTouch
import com.apm.trackify.util.extension.toast
import com.apm.trackify.util.extension.toggleVisibility

class TrackAddViewHolder(private val binding: PlaylistsTrackAddItemBinding) :
    DelegateViewHolder<Track>(binding) {

    init {
        scaleOnTouch()
    }

    override fun bind(item: Track) {
        // TODO: replace toast with track playback
        binding.root.setOnClickListener { it.context.toast("PLAYBACK ${item.name}") }

        binding.cover.loadFromURI(item.imageUri, R.drawable.placeholder_track)
        binding.name.text = item.name
        binding.artists.text = item.artists
        binding.explicit.toggleVisibility(item.explicit, true)
    }
}