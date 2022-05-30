package com.apm.trackify.ui.routes.landing.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.RoutesPlaylistsItemBinding
import com.apm.trackify.provider.model.MockProvider
import com.apm.trackify.provider.model.diff.RouteItemDiffUtil
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.service.firebase.FirebaseService
import com.apm.trackify.provider.service.spotify.SpotifyApi
import com.apm.trackify.ui.routes.landing.RoutesLandingFragmentDirections
import com.apm.trackify.ui.routes.landing.view.holder.PlaylistRouteViewHolder
import com.apm.trackify.ui.user.landing.UserLandingFragmentDirections
import com.apm.trackify.util.extension.loadFromURI
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaylistRouteAdapter (
    spotifyApi: SpotifyApi,
    mapsDraw: (List<LatLng>) -> Unit
) : ListAdapter<RouteItem, PlaylistRouteViewHolder>(RouteItemDiffUtil()) {

    private val draw = mapsDraw
    private val mySpotifyApi = spotifyApi

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRouteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutesPlaylistsItemBinding.inflate(inflater, parent, false)
        return PlaylistRouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistRouteViewHolder, position: Int) {
        val route = getItem(position)
        holder.nameTextView.text = route.title
        holder.ownerTextView.text = route.creator

        CoroutineScope(Dispatchers.IO).launch {
            val response = mySpotifyApi.getPlaylistById(route.playlistId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val playlist = response.body()?.toPlaylistItem()
                    if (playlist != null) {
                        val image = playlist.imageUri
                        holder.coverImageView.loadFromURI(image, R.drawable.placeholder_playlist)
                        holder.more.setOnClickListener {
                            val navController = it.findNavController()
                            val action = RoutesLandingFragmentDirections.toPlaylistTrackFragment(playlist)
                            navController.navigate(action)
                        }
                    }
                }
            }
        }

        holder.itemView.setOnClickListener {
            draw(route.coordinates)
        }
    }
}