package com.apm.trackify.ui.user.landing.sharedRoutes.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.util.extension.scaleOnTouch

class UserSharedRouteViewHolder(
    val binding: UserSharedRouteItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    val icon = binding.selected
    val coverImageView = binding.cover
    val nameTextView = binding.name
    val moreButton = binding.more

    init {
        scaleOnTouch()
    }
}