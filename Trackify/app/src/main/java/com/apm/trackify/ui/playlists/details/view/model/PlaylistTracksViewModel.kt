package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.ViewModel
import androidx.paging.insertFooterItem
import androidx.paging.map
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.UiModel
import com.apm.trackify.provider.repository.SpotifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PlaylistTracksViewModel @Inject constructor(
    playlistItem: PlaylistItem,
    spotifyRepository: SpotifyRepository
) : ViewModel() {

    val tracksFlow =
        spotifyRepository
            .getPlaylistTracks(playlistItem.id)
            .map { pagingData ->
                {
                    var duration = 0
                    pagingData
                        .map {
                            duration += it.duration_ms
                            UiModel.TrackItem(it) as UiModel
                        }
                        .insertFooterItem(item = UiModel.Footer("$duration time"))
                }
            }
}