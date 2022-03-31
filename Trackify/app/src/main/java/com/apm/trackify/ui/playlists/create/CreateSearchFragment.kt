package com.apm.trackify.ui.playlists.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateSearchFragmentBinding

class CreateSearchFragment : Fragment() {
    private lateinit var binding: PlaylistsCreateSearchFragmentBinding

    private lateinit var navc: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlaylistsCreateSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsCreateSearchFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
    }

    private fun setupToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.playlists_fragment,
                R.id.routes_fragment,
                R.id.profile_fragment
            )
        )

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}