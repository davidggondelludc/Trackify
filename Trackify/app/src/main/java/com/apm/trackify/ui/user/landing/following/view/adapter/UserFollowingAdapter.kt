package com.apm.trackify.ui.user.landing.following.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.model.diff.UserItemDiffUtil
import com.apm.trackify.model.domain.UserItem
import com.apm.trackify.ui.user.landing.following.view.holder.UserFollowingViewHolder
import com.apm.trackify.util.extension.toast

class UserFollowingAdapter() : ListAdapter<UserItem, UserFollowingViewHolder>(UserItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserFollowingItemBinding.inflate(inflater, parent, false)

        return UserFollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserFollowingViewHolder, position: Int) {
        val user = getItem(position)

        holder.nameTextView.text = user.userName
        holder.sharedPlaylistsTextView.text = "${user.routes.size} shared playlists"

        holder.itemView.setOnClickListener {
            it.context.toast("Go to user profile")
        }
    }
}