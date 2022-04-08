package com.apm.trackify.ui.user.landing.sharedRoutes.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.model.diff.RouteDiffUtil
import com.apm.trackify.model.domain.Route
import com.apm.trackify.ui.user.landing.sharedRoutes.view.holder.UserSharedRouteViewHolder
import com.apm.trackify.util.base.DelegateAdapter

class UserSharedRouteAdapter : DelegateAdapter<Route, UserSharedRouteViewHolder>(RouteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSharedRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserSharedRouteItemBinding.inflate(inflater, parent, false)

        return UserSharedRouteViewHolder(binding)
    }
}