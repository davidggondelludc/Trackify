package com.apm.trackify.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.model.domain.RouteItem

class RouteItemDiffUtil : DiffUtil.ItemCallback<RouteItem>() {

    override fun areItemsTheSame(oldItem: RouteItem, newItem: RouteItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RouteItem, newItem: RouteItem): Boolean =
        oldItem == newItem
}