package com.apm.trackify.ui.playlists.navigation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistCreateFragmentBinding
import com.apm.trackify.util.extension.toast

class PlaylistCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PlaylistCreateFragmentBinding.inflate(inflater, container, false)

        binding.search.setOnClickListener { it.context.toast("Showing results") }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = PlaylistCreateFragmentBinding.bind(view)

        binding.save.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_navigation_create_to_navigation_landing)
        }
    }
}