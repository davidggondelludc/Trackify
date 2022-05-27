package com.apm.trackify.ui.playlists.details.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTrackHeaderItemBinding
import com.apm.trackify.provider.model.diff.PlaylistItemDiffUtil
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.ui.playlists.details.view.holder.HeaderViewHolder
import com.apm.trackify.ui.playlists.details.view.model.PlaylistTracksViewModel
import com.apm.trackify.ui.playlists.details.view.model.enum.SortType
import com.apm.trackify.util.extension.loadFromURI

class HeaderAdapter(
    private val viewModel: PlaylistTracksViewModel
) : ListAdapter<PlaylistItem, HeaderViewHolder>(PlaylistItemDiffUtil()) {

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

        holder.sortByTextView.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, holder.sortByTextView)
            popupMenu.menuInflater.inflate(R.menu.popup_sort_by_menu, popupMenu.menu)
            when (viewModel.sortType) {
                SortType.TITLE -> popupMenu.menu.findItem(R.id.by_title).isChecked = true
                SortType.ARTIST -> popupMenu.menu.findItem(R.id.by_artist).isChecked = true
                SortType.DURATION -> popupMenu.menu.findItem(R.id.by_duration).isChecked = true
                SortType.RECENTLY_ADDED -> popupMenu.menu.findItem(R.id.by_recently_added).isChecked =
                    true
                SortType.CUSTOM -> popupMenu.menu.findItem(R.id.by_custom).isChecked = true
            }
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.by_title -> viewModel.sort(SortType.TITLE, null)
                    R.id.by_artist -> viewModel.sort(SortType.ARTIST, null)
                    R.id.by_duration -> viewModel.sort(SortType.DURATION, null)
                    R.id.by_recently_added -> viewModel.sort(SortType.RECENTLY_ADDED, null)
                    R.id.by_custom -> viewModel.sort(SortType.CUSTOM, null)
                    else -> throw IllegalArgumentException("Sort type not exist.")
                }
                true
            }
            popupMenu.show()
        }

        holder.sortOrderButton.setOnClickListener {
            if (it.tag == R.drawable.ic_arrow_down) {
                it.tag = R.drawable.ic_arrow_up
                holder.sortOrderButton.setImageResource(R.drawable.ic_arrow_up)
                viewModel.sort(null, true)
            } else {
                it.tag = R.drawable.ic_arrow_down
                holder.sortOrderButton.setImageResource(R.drawable.ic_arrow_down)
                viewModel.sort(null, false)
            }
        }
    }
}