package com.apm.trackify.ui.playlist

import com.apm.trackify.ui.playlist.adapter.CustomAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.R
import com.apm.trackify.ui.playlist.model.PlaylistCoverItems

class PlaylistLandingFragment : Fragment() {

    private lateinit var binding: PlaylistsLandingFragmentBinding
    private lateinit var navc: NavController
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlaylistsLandingFragmentBinding.inflate(inflater, container, false)
        val recyclerview = binding.rvPlaylistCover

        recyclerview.layoutManager = GridLayoutManager(context,2,RecyclerView.VERTICAL,false)

        val data = ArrayList<PlaylistCoverItems>()

        for (i in 1..20) {
            data.add(PlaylistCoverItems(R.drawable.ic_album, "Playlist " + i))
        }

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navc = Navigation.findNavController(view)
        binding.button.setOnClickListener {
            navc.navigate(R.id.action_navigation_landing_to_navigation_create)
        }
    }
}