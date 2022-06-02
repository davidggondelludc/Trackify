package com.apm.trackify.ui.playlists.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsLandingFragmentBinding
import com.apm.trackify.ui.playlists.landing.view.adapter.PlaylistAdapter
import com.apm.trackify.ui.playlists.landing.view.model.PlaylistsLandingViewModel
import com.apm.trackify.util.ScreenUtils
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toastError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistsLandingFragment : Fragment() {

    private val viewModel: PlaylistsLandingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsLandingFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = PlaylistsLandingFragmentBinding.bind(view)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add -> {
                    val navController = findNavController()
                    val action = PlaylistsLandingFragmentDirections.toPlaylistCreateFragment()
                    navController.navigate(action)
                }
            }
            true
        }
        setupToolbar(binding.toolbar)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMePlaylists()
            binding.swipeRefresh.isRefreshing = true
        }

        val screenUtils = ScreenUtils()
        val screenWidth = screenUtils.getScreenWidth()

        var span = 2
        if (screenWidth > 1800) {
            span = 4
        }

        val playlistsAdapter = PlaylistAdapter()

        binding.playlists.adapter = playlistsAdapter
        binding.playlists.layoutManager =
            GridLayoutManager(requireContext(), span, RecyclerView.VERTICAL, false)

        viewModel.error.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            requireContext().toastError(it)
        }
        viewModel.playlists.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            playlistsAdapter.submitList(it)
        }
    }
}