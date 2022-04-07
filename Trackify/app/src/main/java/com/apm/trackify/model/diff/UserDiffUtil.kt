package com.apm.trackify.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.apm.trackify.model.domain.User

class UserDiffUtil : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem == newItem
}