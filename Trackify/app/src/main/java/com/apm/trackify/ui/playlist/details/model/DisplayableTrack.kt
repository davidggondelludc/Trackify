package com.apm.trackify.ui.playlist.details.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.extensions.loadFromURI
import com.apm.trackify.base.extensions.toggleVisibility
import com.apm.trackify.databinding.PlaylistsDetailsTrackItemBinding

data class DisplayableTrack(
    override val type: Int = R.layout.playlists_details_track_item,
    val imageURI: String,
    val name: String,
    val artists: String,
    val explicit: Boolean,
    val duration: Int
) : BaseModel {

    override fun bind(view: View, position: Int) {
        view.apply {
            val binding = PlaylistsDetailsTrackItemBinding.bind(view)

            binding.cover.loadFromURI(imageURI, R.drawable.placeholder_musical_note)
            binding.name.text = name
            binding.artists.text = artists
            binding.explicit.toggleVisibility(explicit, true)
        }
    }
}