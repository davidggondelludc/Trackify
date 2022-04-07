package com.apm.trackify.ui.playlists.details.view.holder

import androidx.recyclerview.widget.ItemTouchHelper
import com.apm.trackify.R
import com.apm.trackify.base.DelegateViewHolder
import com.apm.trackify.databinding.PlaylistsTrackDragItemBinding
import com.apm.trackify.model.domain.Track
import com.apm.trackify.util.extension.*

class TrackViewHolder(
    private val binding: PlaylistsTrackDragItemBinding,
    itemTouchHelper: ItemTouchHelper
) : DelegateViewHolder<Track>(binding.root) {

    init {
        scaleOnTouch()
        setOnDragListener(binding.drag, itemTouchHelper)
    }

    override fun bind(item: Track) {
        // TODO: replace toast with track playback
        binding.root.setOnClickListener { it.context.toast("PLAYBACK ${item.name}") }

        binding.cover.loadFromURI(item.imageUri, R.drawable.placeholder_musical_note)
        binding.name.text = item.name
        binding.artists.text = item.artists
        binding.explicit.toggleVisibility(item.explicit, true)
    }
}