package com.apm.trackify.ui.user.landing.sharedRoutes.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.model.diff.RouteItemDiffUtil
import com.apm.trackify.model.domain.RouteItem
import com.apm.trackify.service.firebase.FirebaseService
import com.apm.trackify.service.spotify.SpotifyService
import com.apm.trackify.ui.user.landing.UserLandingFragmentDirections
import com.apm.trackify.ui.user.landing.sharedRoutes.view.holder.UserSharedRouteViewHolder
import com.apm.trackify.util.CoverUtil
import com.apm.trackify.util.extension.toast
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserSharedRouteAdapter(
    reload: () -> Unit,
    spotifyService: SpotifyService,
    navController: NavController,
    mapsDraw: (List<LatLng>) -> Unit
) :
    ListAdapter<RouteItem, UserSharedRouteViewHolder>(RouteItemDiffUtil()) {

    private val myreload = reload
    private val firebaseService = FirebaseService()
    private val mySpotifyService = spotifyService
    private val navController = navController
    private val draw = mapsDraw

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
        holder.followersTextView.text = route.creator

        holder.itemView.setOnClickListener {
            draw(route.coordinates)
        }

        holder.moreButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, holder.moreButton)
            popupMenu.menuInflater.inflate(R.menu.popup_route_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.viewPlaylist -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            val response = mySpotifyService.getPlaylistById(route.playlistId)

                            withContext(Dispatchers.Main) {
                                if (response.isSuccessful) {
                                    val playlist = response.body()?.toPlaylistItem()
                                    if (playlist != null) {
                                        val action =
                                            UserLandingFragmentDirections.actionUserFragmentToPlaylistTracksFragment(
                                                playlist
                                            )
                                        navController.navigate(action)
                                    }
                                }
                            }
                        }
                        true
                    }
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
                        firebaseService.deleteRoute(
                            route.id,
                            {
                                view.context.toast("Route deleted")
                                myreload()
                            },
                            { view.context.toast("Could not delete route") })

                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}