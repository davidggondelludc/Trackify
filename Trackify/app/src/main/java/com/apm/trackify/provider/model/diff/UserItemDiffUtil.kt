package com.apm.trackify.provider.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.provider.model.domain.UserItem

class UserItemDiffUtil : DiffUtil.ItemCallback<UserItem>() {

    override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean =
        oldItem.userName == newItem.userName

    override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean =
        oldItem == newItem
}