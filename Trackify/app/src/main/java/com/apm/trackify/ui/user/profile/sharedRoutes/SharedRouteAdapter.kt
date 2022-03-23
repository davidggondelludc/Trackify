package com.apm.trackify.ui.user.profile.sharedRoutes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.extensions.toast


class SharedRouteAdapter():
    ListAdapter<SharedRoute, SharedRouteAdapter.SharedRouteViewHolder>(SharedRouteAdapter.SharedRouteDiffUtil()) {

    class SharedRouteDiffUtil(): DiffUtil.ItemCallback<SharedRoute>() {
        override fun areItemsTheSame(oldItem: SharedRoute, newItem: SharedRoute): Boolean {
            return oldItem.title == newItem.title
            //TODO: meter id en vez de title
        }

        override fun areContentsTheSame(oldItem: SharedRoute, newItem: SharedRoute): Boolean {
            return oldItem == newItem
        }
    }
    inner class SharedRouteViewHolder(binding: UserSharedRouteItemBinding): RecyclerView.ViewHolder(binding.root) {
        val titleTextView: TextView = binding.userPlaylist
        val playlistFollowersTextView: TextView = binding.twPlaylistFollowers

        init {
            binding.root.setOnClickListener { it.context.toast("Show map of the route") }
            binding.sharedRouteItemShare.setOnClickListener{ it.context.toast("Share playlist") }
            binding.sharedRouteItemDelete.setOnClickListener{ it.context.toast("Delete shared playlist") }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedRouteViewHolder {
        return SharedRouteViewHolder(
            UserSharedRouteItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun onBindViewHolder(holder: SharedRouteViewHolder, position: Int) {
        val currentSharedRoute = getItem(position)
        holder.titleTextView.text = currentSharedRoute.title
        holder.playlistFollowersTextView.text = "Followers ${currentSharedRoute.followers}"
    }
}