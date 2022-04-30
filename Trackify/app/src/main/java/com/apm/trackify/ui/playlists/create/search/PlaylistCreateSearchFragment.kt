package com.apm.trackify.ui.playlists.create.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsCreateSearchFragmentBinding
import com.apm.trackify.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.create.search.view.adapter.TrackAddAdapter
import com.apm.trackify.ui.playlists.create.search.view.model.PlaylistCreateSearchViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistCreateSearchFragment : Fragment() {

    @Inject
    lateinit var mediaServiceLifecycle: MediaServiceLifecycle
    private val viewModel: PlaylistCreateSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsCreateSearchFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(mediaServiceLifecycle)

        val binding = PlaylistsCreateSearchFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        setupRecyclerView(binding.rvSearchedSongs)

        binding.search.setOnEditorActionListener { textView, i, _ ->
            when (i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    binding.searchProgressBar.visibility = View.VISIBLE
                    viewModel.searchTracks(textView.text.toString())
                    binding.searchProgressBar.visibility = View.GONE
                    hideKeyboard()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val addTrackAdapter = TrackAddAdapter(mediaServiceLifecycle)
        viewModel.getResponse().observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                addTrackAdapter.submitList(it.body()?.toTrackItems())
            } else {
                context?.toast(R.string.error)
            }
        }

        recyclerView.adapter = addTrackAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        view?.clearFocus()
    }
}