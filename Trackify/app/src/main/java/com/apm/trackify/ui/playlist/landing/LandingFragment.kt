package com.apm.trackify.ui.playlist.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.R

class LandingFragment : Fragment() {

    private lateinit var binding: PlaylistsLandingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlaylistsLandingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        binding.playlistBtn.setOnClickListener { navController.navigate(R.id.action_navigation_landing_to_navigation_details) }
        binding.btnCreateNewPlaylist.setOnClickListener { navController.navigate(R.id.action_navigation_landing_to_navigation_create) }
    }
}