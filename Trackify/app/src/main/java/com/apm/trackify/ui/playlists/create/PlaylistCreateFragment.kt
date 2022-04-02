package com.apm.trackify.ui.playlists.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateFragmentBinding
import com.apm.trackify.util.extension.setupToolbar

class PlaylistCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsCreateFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)

        binding.saveButton.setOnClickListener {
            val navController = it.findNavController()
            navController.navigateUp()
        }
        binding.formSearchButton.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.to_playlist_create_search_fragment)
        }
    }
}