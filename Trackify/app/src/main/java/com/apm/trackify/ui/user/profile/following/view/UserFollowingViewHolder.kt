package com.apm.trackify.ui.user.profile.following.view

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.model.domain.User
import com.apm.trackify.util.extension.toast

class UserFollowingViewHolder(val binding: UserFollowingItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.imgBtnSeeUserFollowing.setOnClickListener { it.context.toast("Go to user profile") }
    }

    fun bind(user: User) {
        binding.tvUsename.text = user.name
        binding.tvUserPlaylists.text = "${user.sharedPlaylists} shared playlists"
    }
}