package com.apm.trackify.ui.user.landing.following.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.model.diff.UserDiffUtil
import com.apm.trackify.model.domain.User
import com.apm.trackify.ui.user.landing.following.view.holder.UserFollowingViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class UserFollowingAdapter() : DelegateAdapter<User, UserFollowingViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserFollowingItemBinding.inflate(inflater, parent, false)

        return UserFollowingViewHolder(binding)
    }
}