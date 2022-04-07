package com.apm.trackify.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.model.domain.Route

class RouteDiffUtil : DiffUtil.ItemCallback<Route>() {

    override fun areItemsTheSame(oldItem: Route, newItem: Route): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Route, newItem: Route): Boolean =
        oldItem == newItem
}