package com.apm.trackify.ui.user.landing.following.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.util.extension.scaleOnTouch

class UserFollowingViewHolder(
    val binding: UserFollowingItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    val nameTextView = binding.name
    val sharedPlaylistsTextView = binding.sharedPlaylists

    init {
        scaleOnTouch()
    }
}