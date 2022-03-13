package com.apm.trackify.playlist.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.extensions.toggleVisibility
import com.apm.trackify.databinding.ItemPlaylistTrackBinding

data class DisplayableTrack(
    override val type: Int = R.layout.item_playlist_track,
    val title: String,
    val subtitle: String,
    val explicit: Boolean,
) : BaseModel {

    override fun bind(view: View, position: Int) {
        val binding = ItemPlaylistTrackBinding.bind(view)

        view.apply {
            binding.title.text = title
            binding.subtitle.text = subtitle
            binding.explicit.toggleVisibility(explicit, true)
        }
    }
}