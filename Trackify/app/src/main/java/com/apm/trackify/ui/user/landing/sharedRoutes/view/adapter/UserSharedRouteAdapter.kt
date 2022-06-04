package com.apm.trackify.ui.user.landing.sharedRoutes.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.UserSharedRouteItemBinding
import com.apm.trackify.provider.model.diff.RouteItemDiffUtil
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.ui.user.landing.UserLandingFragmentDirections
import com.apm.trackify.ui.user.landing.sharedRoutes.view.holder.UserSharedRouteViewHolder
import com.apm.trackify.util.CoverUtil
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.toastError
import com.apm.trackify.util.extension.toastSuccess
import com.apm.trackify.util.maps.MapsUtil
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserSharedRouteAdapter(
    private val reload: () -> Unit,
    private val spotifyApi: SpotifyApi,
    private val navController: NavController,
    private val mapsDraw: (List<LatLng>) -> Unit,
    private val me: Boolean
) : ListAdapter<RouteItem, UserSharedRouteViewHolder>(RouteItemDiffUtil()) {

    private val firebaseService = FirebaseService()
    private var selectedPosition = 0
    private lateinit var imageMap: MutableMap<Int, String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSharedRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserSharedRouteItemBinding.inflate(inflater, parent, false)

        return UserSharedRouteViewHolder(binding)
    }

    override fun onCurrentListChanged(
        previousList: MutableList<RouteItem>,
        currentList: MutableList<RouteItem>
    ) {
        imageMap = mutableMapOf()
        currentList.forEachIndexed { count, it ->
            selectedPosition = 0
            if (count == 0) {
                mapsDraw(it.coordinates)
            }
            CoroutineScope(Dispatchers.IO).launch {
                val response = spotifyApi.getPlaylistById(it.playlistId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val playlist = response.body()?.toPlaylistItem()
                        if (playlist != null) {
                            val image = playlist.imageUri
                            imageMap[count] = image
                            notifyItemChanged(count)
                        }
                    }
                }
            }
        }
        super.onCurrentListChanged(previousList, currentList)
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
        holder.icon.setImageDrawable(holder.itemView.context.getDrawable(R.drawable.ic_star_four_points))

        holder.nameTextView.text = route.title
        if (selectedPosition == position) {
            holder.icon.visibility = View.VISIBLE
        } else {
            holder.icon.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            mapsDraw(route.coordinates)
            selectedPosition = holder.bindingAdapterPosition
            notifyDataSetChanged()
        }

        imageMap[position]?.let {
            holder.coverImageView.loadFromURI(
                it,
                R.drawable.placeholder_playlist
            )
        }

        holder.moreButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, holder.moreButton)
            if (me) {
                popupMenu.menuInflater.inflate(R.menu.popup_route_menu, popupMenu.menu)
            } else {
                popupMenu.menuInflater.inflate(
                    R.menu.popup_route_menu_without_delete,
                    popupMenu.menu
                )
            }

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.viewPlaylist -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            val response = spotifyApi.getPlaylistById(route.playlistId)

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
                                "${view.resources.getText(R.string.share_route_text)}\nRoute: ${
                                    MapsUtil.getRouteURL(
                                        route.coordinates
                                    )
                                }\nPlaylist: https://open.spotify.com/playlist/${route.playlistId}"
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
                        if (me) {
                            firebaseService.deleteRoute(
                                route.id,
                                {
                                    view.context.toastSuccess(R.string.route_delete_success)
                                    reload()
                                },
                                {
                                    view.context.toastError(R.string.route_delete_error)
                                })
                        }
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}