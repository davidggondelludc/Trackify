package com.apm.trackify.ui.playlists.create.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.apm.trackify.databinding.PlaylistsCreateHeaderBinding
import com.apm.trackify.provider.model.diff.PlaylistItemDiffUtil
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.repository.denum.Duration
import com.apm.trackify.ui.playlists.create.PlaylistCreateFragmentDirections
import com.apm.trackify.ui.playlists.create.view.holder.HeaderViewHolder

class HeaderAdapter : ListAdapter<PlaylistItem, HeaderViewHolder>(PlaylistItemDiffUtil()) {

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
                var selectedDuration = spinnerAdapter.getItem(position)
                Toast.makeText(parent?.context, selectedDuration.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.searchButton.setOnClickListener {
            val navController = it.findNavController()
            val action = PlaylistCreateFragmentDirections.toPlaylistCreateSearchFragment()
            navController.navigate(action)
        }
    }


}