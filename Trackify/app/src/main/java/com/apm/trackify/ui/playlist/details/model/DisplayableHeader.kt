package com.apm.trackify.ui.playlist.details.model

import android.view.View
import com.apm.trackify.R
import com.apm.trackify.base.adapter.BaseModel
import com.apm.trackify.base.extensions.loadFromURI
import com.apm.trackify.base.extensions.toast
import com.apm.trackify.databinding.PlaylistsDetailsHeaderItemBinding

data class DisplayableHeader(
    override val type: Int = R.layout.playlists_details_header_item,
    val imageURI: String,
    val title: String
) : BaseModel {

    override fun bind(view: View, position: Int) {
        val binding = PlaylistsDetailsHeaderItemBinding.bind(view)

        binding.cover.loadFromURI(imageURI, R.drawable.placeholder_album)
        binding.title.text = title

        // TODO: replace toasts with correct interaction
        binding.spotify.setOnClickListener { it.context.toast("GO TO SPOTIFY") }
        binding.unfollow.setOnClickListener { it.context.toast("UNFOLLOW USER") }
    }
}