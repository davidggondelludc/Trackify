package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
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

    private lateinit var spinnerAdapter: ArrayAdapter<Duration>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlaylistsCreateHeaderBinding.inflate(inflater, parent, false)

        spinnerAdapter = ArrayAdapter(
            parent.context,
            android.R.layout.simple_spinner_dropdown_item,
            Duration.values()
        )
        binding.timeSpinner.adapter = spinnerAdapter
        binding.timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val duration = spinnerAdapter.getItem(position)!!
                viewModel.generateTracklist(duration)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

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