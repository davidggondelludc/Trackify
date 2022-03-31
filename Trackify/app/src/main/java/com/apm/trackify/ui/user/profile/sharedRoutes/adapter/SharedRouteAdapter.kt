package com.apm.trackify.ui.user.profile.sharedRoutes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.model.diff.RouteDiffUtil
import com.apm.trackify.model.domain.Route
import com.apm.trackify.ui.user.profile.sharedRoutes.view.SharedRouteViewHolder


class SharedRouteAdapter : ListAdapter<Route, SharedRouteViewHolder>(RouteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedRouteViewHolder {
        return SharedRouteViewHolder(
            UserSharedRouteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SharedRouteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}