package com.apm.trackify.ui.user.profile.sharedRoutes.view

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.model.domain.Route
import com.apm.trackify.util.extension.toast

class SharedRouteViewHolder(val binding: UserSharedRouteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { it.context.toast("Show map of the route") }
        binding.sharedRouteItemShare.setOnClickListener { it.context.toast("Share playlist") }
        binding.sharedRouteItemDelete.setOnClickListener { it.context.toast("Delete shared playlist") }
    }

    fun bind(sharedRoute: Route) {
        binding.userPlaylist.text = sharedRoute.title
        binding.twPlaylistFollowers.text = "Followers ${sharedRoute.followers}"
    }
}