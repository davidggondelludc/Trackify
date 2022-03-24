package com.apm.trackify.ui.user.profile.following

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserFollowingItemBinding
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.extensions.toast
import com.apm.trackify.ui.user.profile.sharedRoutes.SharedRoute
import com.apm.trackify.ui.user.profile.sharedRoutes.SharedRouteAdapter

class UserFollowingAdapter():
    ListAdapter<UserFollowing, UserFollowingAdapter.UserFollowingViewHolder>(UserFollowingAdapter.UserFollowingDiffUtil()) {

    class UserFollowingDiffUtil(): DiffUtil.ItemCallback<UserFollowing>() {
        override fun areItemsTheSame(oldItem: UserFollowing, newItem: UserFollowing): Boolean {
            return oldItem.username == newItem.username
            //TODO: meter id en vez de title
        }

        override fun areContentsTheSame(oldItem: UserFollowing, newItem: UserFollowing): Boolean {
            return oldItem == newItem
        }
    }
    inner class UserFollowingViewHolder(binding: UserFollowingItemBinding): RecyclerView.ViewHolder(binding.root) {
        val userNameTextView: TextView = binding.tvUsename
        val playlistFollowersTextView: TextView = binding.tvUserPlaylists

        init {
            binding.imgBtnSeeUserFollowing.setOnClickListener{ it.context.toast("Go to user profile") }
            //binding.sharedRouteItemDelete.setOnClickListener{ it.context.toast("Delete shared playlist") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowingViewHolder {
        return UserFollowingViewHolder(
            UserFollowingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserFollowingViewHolder, position: Int) {
        val currentUserFollowing = getItem(position)
        holder.userNameTextView.text = currentUserFollowing.username
        holder.playlistFollowersTextView.text = "${currentUserFollowing.sharedPlaylists} shared playlists"
    }
}