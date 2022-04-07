package com.apm.trackify.ui.user.landing.sharedRoutes.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.model.diff.RouteDiffUtil
import com.apm.trackify.model.domain.Route
import com.apm.trackify.ui.user.landing.sharedRoutes.view.holder.UserSharedRouteViewHolder

class UserSharedRouteAdapter : ListAdapter<Route, UserSharedRouteViewHolder>(RouteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSharedRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserSharedRouteItemBinding.inflate(inflater, parent, false)

        return UserSharedRouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserSharedRouteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}