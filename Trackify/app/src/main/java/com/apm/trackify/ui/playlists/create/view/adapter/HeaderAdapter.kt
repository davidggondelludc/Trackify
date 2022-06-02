package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateHeaderBinding
import com.apm.trackify.provider.model.diff.PlaylistRequestItemDiffUtil
import com.apm.trackify.provider.model.domain.PlaylistRequestItem
import com.apm.trackify.provider.repository.data.Duration
import com.apm.trackify.ui.playlists.create.PlaylistCreateFragmentDirections
import com.apm.trackify.ui.playlists.create.view.holder.HeaderViewHolder
import com.apm.trackify.ui.playlists.create.view.model.PlaylistCreateViewModel

class HeaderAdapter(
    private val viewModel: PlaylistCreateViewModel
) : ListAdapter<PlaylistRequestItem, HeaderViewHolder>(PlaylistRequestItemDiffUtil()) {

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

        ArrayAdapter.createFromResource(
            holder.itemView.context,
            R.array.duration_list,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.durationSpinner.adapter = it
            holder.durationSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when (position) {
                            0 -> viewModel.generateTracklist(Duration.SHORT)
                            1 -> viewModel.generateTracklist(Duration.MEDIUM)
                            else -> viewModel.generateTracklist(Duration.LONG)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            when (playlist.duration) {
                Duration.SHORT -> holder.durationSpinner.setSelection(0)
                Duration.MEDIUM -> holder.durationSpinner.setSelection(1)
                Duration.LONG -> holder.durationSpinner.setSelection(2)
            }
        }

        holder.searchButton.setOnClickListener {
            val navController = it.findNavController()
            val action = PlaylistCreateFragmentDirections.toPlaylistCreateSearchFragment()
            navController.navigate(action)
        }
    }
}