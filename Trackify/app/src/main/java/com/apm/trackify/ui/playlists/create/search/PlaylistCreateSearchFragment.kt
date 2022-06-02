package com.apm.trackify.ui.playlists.create.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.apm.trackify.databinding.PlaylistsCreateSearchFragmentBinding
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.search.view.adapter.TrackAddAdapter
import com.apm.trackify.ui.playlists.create.search.view.model.PlaylistCreateSearchViewModel
import com.apm.trackify.ui.playlists.create.view.model.PlaylistCreateViewModel
import com.apm.trackify.util.extension.setupToolbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistCreateSearchFragment : Fragment() {

    @Inject
    lateinit var mediaServiceLifecycle: MediaServiceLifecycle
    private val viewModel: PlaylistCreateSearchViewModel by viewModels()
    private val sharedViewModel: PlaylistCreateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateSearchFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(mediaServiceLifecycle)

        val binding = PlaylistsCreateSearchFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)

        val addTrackAdapter = TrackAddAdapter(sharedViewModel, mediaServiceLifecycle)

        viewModel.tracks.observe(viewLifecycleOwner) {
            binding.searchProgressBar.visibility = View.GONE
            addTrackAdapter.submitList(it)
        }

        binding.rvSearchedSongs.adapter = addTrackAdapter
        binding.rvSearchedSongs.layoutManager = LinearLayoutManager(context)

        binding.search.setOnEditorActionListener { textView, i, _ ->
            if (textView.text.isNotEmpty()) {
                when (i) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        binding.searchProgressBar.visibility = View.VISIBLE
                        viewModel.searchTracks(textView.text.toString())
                        hideKeyboard()
                        true
                    }
                    else -> false
                }
            } else false
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
        requireView().clearFocus()
    }
}