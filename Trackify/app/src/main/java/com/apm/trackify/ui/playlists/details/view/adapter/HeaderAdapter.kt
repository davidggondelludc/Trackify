package com.apm.trackify.ui.playlists.details.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackHeaderItemBinding
import com.apm.trackify.model.diff.PlaylistDiffUtil
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.details.view.holder.HeaderViewHolder
import com.apm.trackify.util.extension.loadFromURI
import com.apm.trackify.util.extension.toast

class HeaderAdapter : ListAdapter<Playlist, HeaderViewHolder>(PlaylistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsTrackHeaderItemBinding.inflate(inflater, parent, false)

        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        val playlist = getItem(position)

        holder.coverImageView.loadFromURI(playlist.imageUri, R.drawable.placeholder_playlist)
        holder.nameTextView.text = playlist.name
        holder.ownerTextView.text = playlist.owner

        holder.spotifyButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(playlist.playlistUri))
            startActivity(holder.itemView.context, intent, null)
        }

        holder.unfollowButton.setOnClickListener {
            it.context.toast("UNFOLLOW")
        }
    }
}