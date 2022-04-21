package com.apm.trackify.ui.playlists.landing.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsItemBinding
import com.apm.trackify.model.diff.PlaylistDiffUtil
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.landing.PlaylistsLandingFragmentDirections
import com.apm.trackify.ui.playlists.landing.view.holder.PlaylistViewHolder
import com.apm.trackify.util.extension.loadFromURI

class PlaylistAdapter : ListAdapter<Playlist, PlaylistViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsItemBinding.inflate(inflater, parent, false)

        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = getItem(position)

        holder.coverImageView.loadFromURI(playlist.imageUri, R.drawable.placeholder_playlist)
        holder.nameTextView.text = playlist.name
        holder.totalTracksTextView.text = holder.itemView.resources.getQuantityString(
            R.plurals.tracks,
            playlist.totalTracks,
            playlist.totalTracks
        )

        holder.itemView.setOnClickListener {
            val navController = it.findNavController()
            val action = PlaylistsLandingFragmentDirections.toPlaylistTrackFragment(playlist)
            navController.navigate(action)
        }
    }
}