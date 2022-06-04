package com.apm.trackify.ui.routes.landing.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.provider.model.diff.RouteItemDiffUtil
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.ui.routes.landing.RoutesLandingFragmentDirections
import com.apm.trackify.ui.routes.landing.view.holder.PlaylistRouteViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaylistRouteAdapter(
    private val spotifyApi: SpotifyApi,
    private val mapsDraw: (List<LatLng>) -> Unit
) : ListAdapter<RouteItem, PlaylistRouteViewHolder>(RouteItemDiffUtil()) {

    private lateinit var playlistMap: MutableMap<Int, PlaylistItem>
    private lateinit var userMap: MutableMap<Int, String>
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutesPlaylistsItemBinding.inflate(inflater, parent, false)
        return PlaylistRouteViewHolder(binding)
    }

    override fun onCurrentListChanged(
        previousList: MutableList<RouteItem>,
        currentList: MutableList<RouteItem>
    ) {
        playlistMap = mutableMapOf()
        userMap = mutableMapOf()
        currentList.forEachIndexed { count, it ->
            if (count == 0) {
                mapsDraw(it.coordinates)
                selectedPosition = 0
            }
            CoroutineScope(Dispatchers.IO).launch {
                val response = spotifyApi.getUserById(it.creator)
                withContext(Dispatchers.Main) {
                    if (response.isSuccess) {
                        userMap[count] = response.getOrThrow().display_name
                        notifyItemChanged(count)
                    }
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                val response = spotifyApi.getPlaylistById(it.playlistId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val playlist = response.body()?.toPlaylistItem()
                        if (playlist != null) {
                            playlistMap[count] = playlist
                            notifyItemChanged(count)
                        }
                    }
                }
            }
        }

        super.onCurrentListChanged(previousList, currentList)
    }

    override fun onBindViewHolder(holder: PlaylistRouteViewHolder, position: Int) {
        val route = getItem(position)
        holder.nameTextView.text = route.title
        holder.ownerTextView.text = route.creator

        holder.icon.setImageDrawable(holder.itemView.context.getDrawable(R.drawable.ic_star_four_points))

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

        playlistMap[position]?.let { playlistItem ->
            holder.coverImageView.loadFromURI(
                playlistItem.imageUri,
                R.drawable.placeholder_playlist
            )
            holder.more.setOnClickListener {
                val navController = it.findNavController()
                val action =
                    RoutesLandingFragmentDirections.toPlaylistTrackFragment(playlistItem)
                navController.navigate(action)
            }
        }
        userMap[position]?.let { userName ->
            holder.ownerTextView.text = userName
        }

        holder.profile.setOnClickListener {
            val navController = it.findNavController()
            val action =
                RoutesLandingFragmentDirections.actionRoutesFragmentToUserFollowingProfileFragment(
                    route.creator
                )
            navController.navigate(action)
        }
    }
}