package com.apm.trackify.ui.user.landing.following.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.provider.model.diff.UserItemDiffUtil
import com.apm.trackify.provider.model.domain.UserItem
import com.apm.trackify.ui.user.landing.UserLandingFragmentDirections
import com.apm.trackify.ui.user.landing.following.view.holder.UserFollowingViewHolder
import com.apm.trackify.util.CoverUtil

class UserFollowingAdapter() : ListAdapter<UserItem, UserFollowingViewHolder>(UserItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserFollowingItemBinding.inflate(inflater, parent, false)

        return UserFollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserFollowingViewHolder, position: Int) {
        val user = getItem(position)

        holder.avatarTextView.background = CoverUtil.getDrawable(
            holder.itemView.context,
            R.drawable.placeholder_empty_drawable,
            user.userName.first().uppercase().hashCode()
        )
        holder.avatarTextView.text = user.userName.first().uppercase()
        holder.nameTextView.text = user.userName
        holder.sharedPlaylistsTextView.text = "${user.routes.size} shared playlists"

        holder.itemView.setOnClickListener {
            val navController = it.findNavController()
            val action =
                UserLandingFragmentDirections.actionUserFragmentToUserFollowingProfileFragment(
                    user.userName
                )
            navController.navigate(action)
        }
    }
}