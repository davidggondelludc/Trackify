package com.apm.trackify.ui.playlist.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apm.trackify.databinding.PlaylistsDetailsFragmentBinding

class PlaylistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PlaylistsDetailsFragmentBinding.inflate(inflater, container, false)

        binding.playlist.layoutManager = LinearLayoutManager(binding.playlist.context)
        binding.playlist.adapter = PlaylistAdapter(PlaylistProvider.playlist.toMutableList())

        return binding.root
    }
}