package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistsCreateHeaderBinding
import com.apm.trackify.provider.model.diff.PlaylistRequestItemDiffUtil
import com.apm.trackify.provider.model.domain.PlaylistRequestItem
import com.apm.trackify.ui.playlists.create.PlaylistCreateFragmentDirections
import com.apm.trackify.ui.playlists.create.view.holder.HeaderViewHolder

class HeaderAdapter :
    ListAdapter<PlaylistRequestItem, HeaderViewHolder>(PlaylistRequestItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsCreateHeaderBinding.inflate(inflater, parent, false)

        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        val playlist = getItem(position)

        holder.nameEditText.doAfterTextChanged {
            playlist.name = it.toString()
        }

        holder.searchButton.setOnClickListener {
            val navController = it.findNavController()
            val action = PlaylistCreateFragmentDirections.toPlaylistCreateSearchFragment()
            navController.navigate(action)
        }
    }
}