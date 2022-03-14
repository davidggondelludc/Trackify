package com.apm.trackify.ui.playlist.details.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.extensions.toggleVisibility
import com.apm.trackify.databinding.PlaylistsDetailsTrackItemBinding

data class DisplayableTrack(
    override val type: Int = R.layout.playlists_details_track_item,
    val title: String,
    val subtitle: String,
    val explicit: Boolean,
) : BaseModel {

    override fun bind(view: View, position: Int) {
        val binding = PlaylistsDetailsTrackItemBinding.bind(view)

        view.apply {
            binding.title.text = title
            binding.subtitle.text = subtitle
            binding.explicit.toggleVisibility(explicit, true)
        }
    }
}