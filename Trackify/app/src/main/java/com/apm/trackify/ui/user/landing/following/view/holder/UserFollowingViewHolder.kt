package com.apm.trackify.ui.user.landing.following.view.holder

import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.model.domain.User
import com.apm.trackify.util.base.DelegateViewHolder
import com.apm.trackify.util.extension.toast

class UserFollowingViewHolder(val binding: UserFollowingItemBinding) :
    DelegateViewHolder<User>(binding) {

    init {
        binding.imgBtnSeeUserFollowing.setOnClickListener { it.context.toast("Go to user profile") }
    }

    override fun bind(item: User) {
        binding.tvUsename.text = item.name
        binding.tvUserPlaylists.text = "${item.sharedPlaylists} shared playlists"
    }
}