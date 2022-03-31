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
            navController.navigate(R.id.playlist_create_fragment_to_playlist_create_search_fragment)
        }
    }

    private fun setupToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playlists_fragment,
                R.id.routes_fragment,
                R.id.user_fragment
            )
        )

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}