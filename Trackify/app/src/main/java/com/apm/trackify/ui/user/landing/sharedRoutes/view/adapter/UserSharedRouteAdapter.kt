package com.apm.trackify.ui.user.landing.sharedRoutes.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.model.diff.RouteDiffUtil
import com.apm.trackify.model.domain.Route
import com.apm.trackify.ui.user.landing.sharedRoutes.view.holder.UserSharedRouteViewHolder
import com.apm.trackify.util.CoverUtil
import com.apm.trackify.util.extension.toast

class UserSharedRouteAdapter : ListAdapter<Route, UserSharedRouteViewHolder>(RouteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSharedRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserSharedRouteItemBinding.inflate(inflater, parent, false)

        return UserSharedRouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserSharedRouteViewHolder, position: Int) {
        val route = getItem(position)

        holder.coverImageView.setImageDrawable(
            CoverUtil.getDrawable(
                holder.itemView.context,
                R.drawable.placeholder_route,
                route.title.hashCode()
            )
        )
        holder.nameTextView.text = route.title
        holder.followersTextView.text = holder.itemView.resources.getQuantityString(
            R.plurals.followers,
            route.followers,
            route.followers
        )

        holder.itemView.setOnClickListener {
            it.context.toast("Show map of the route")
        }

        holder.moreButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, holder.moreButton)
            popupMenu.menuInflater.inflate(R.menu.popup_route_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.share -> {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            putExtra(
                                Intent.EXTRA_TEXT,
                                view.resources.getText(R.string.share_route_text)
                            )
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(
                            intent,
                            view.resources.getText(R.string.share_route_title)
                        )
                        startActivity(view.context, shareIntent, null)
                        true
                    }
                    R.id.delete -> {
                        view.context.toast("DELETE")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}