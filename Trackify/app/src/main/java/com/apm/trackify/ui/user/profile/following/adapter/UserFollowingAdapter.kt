package com.apm.trackify.ui.user.profile.following.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.model.diff.UserDiffUtil
import com.apm.trackify.model.domain.User
import com.apm.trackify.ui.user.profile.following.view.UserFollowingViewHolder

class UserFollowingAdapter() : ListAdapter<User, UserFollowingViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserFollowingItemBinding.inflate(inflater, parent, false)

        return UserFollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserFollowingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}