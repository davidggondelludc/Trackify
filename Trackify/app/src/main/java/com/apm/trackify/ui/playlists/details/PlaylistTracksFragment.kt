package com.apm.trackify.ui.playlists.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apm.trackify.R
import com.apm.trackify.databinding.PlaylistsTracksFragmentBinding
import com.apm.trackify.service.media.MediaServiceLifecycle
import com.apm.trackify.ui.playlists.details.listener.ParallaxListener
import com.apm.trackify.ui.playlists.details.view.adapter.FooterAdapter
import com.apm.trackify.ui.playlists.details.view.adapter.HeaderAdapter
import com.apm.trackify.ui.playlists.details.view.adapter.TrackAdapter
import com.apm.trackify.ui.playlists.details.view.model.PlaylistTracksViewModel
import com.apm.trackify.util.extension.setupToolbar
import com.apm.trackify.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistTracksFragment : Fragment() {

    @Inject
    lateinit var mediaServiceLifecycle: MediaServiceLifecycle

    private val args: PlaylistTracksFragmentArgs by navArgs()
    private val viewModel: PlaylistTracksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = PlaylistsTracksFragmentBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(mediaServiceLifecycle)

        val binding = PlaylistsTracksFragmentBinding.bind(view)

        setupToolbar(binding.toolbar)
        setupRecyclerView(binding.playlist)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val headerAdapter = HeaderAdapter()
        headerAdapter.submitList(listOf(args.playlist))

        val trackAdapter = TrackAdapter(mediaServiceLifecycle)

        val footerAdapter = FooterAdapter()
        viewModel.getResponse().observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val tracks = it.body()?.toTrackItems()!!
                trackAdapter.submitList(tracks)
                footerAdapter.submitList(
                    listOf(
                        generateFooter(
                            tracks.size,
                            tracks.sumOf { track -> track.duration }.toLong()
                        )
                    )
                )
            } else {
                context?.toast(R.string.error)
            }
        }

        recyclerView.adapter = ConcatAdapter(headerAdapter, trackAdapter, footerAdapter)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addOnScrollListener(ParallaxListener())
    }

    private fun generateFooter(tracks: Int, milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes =
            TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours)

        when {
            hours == 0L -> {
                return "${
                    context?.resources?.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    context?.resources?.getQuantityString(
                        R.plurals.minutes,
                        minutes.toInt(),
                        minutes.toInt()
                    )
                }"
            }
            minutes == 0L -> {
                return "${
                    context?.resources?.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    context?.resources?.getQuantityString(
                        R.plurals.hours,
                        hours.toInt(),
                        hours.toInt()
                    )
                }"
            }
            else -> {
                return "${
                    context?.resources?.getQuantityString(
                        R.plurals.tracks,
                        tracks,
                        tracks
                    )
                } · ${
                    context?.resources?.getQuantityString(
                        R.plurals.hours,
                        hours.toInt(),
                        hours.toInt()
                    )
                } ${
                    context?.resources?.getQuantityString(
                        R.plurals.minutes,
                        minutes.toInt(),
                        minutes.toInt()
                    )
                }"
            }
        }
    }
}