package com.apm.trackify.ui.user.landing.sharedRoutes.view.holder

import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.model.domain.Route
import com.apm.trackify.util.base.DelegateViewHolder
import com.apm.trackify.util.extension.toast

class UserSharedRouteViewHolder(override val binding: UserSharedRouteItemBinding) :
    DelegateViewHolder<Route>(binding) {

    init {
        binding.root.setOnClickListener { it.context.toast("Show map of the route") }
        binding.sharedRouteItemShare.setOnClickListener { it.context.toast("Share playlist") }
        binding.sharedRouteItemDelete.setOnClickListener { it.context.toast("Delete shared playlist") }
    }

    override fun bind(item: Route) {
        binding.userPlaylist.text = item.title
        binding.twPlaylistFollowers.text = "Followers ${item.followers}"
    }
}