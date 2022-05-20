package com.apm.trackify.ui.playlists.create.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.model.MockProvider
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.repository.SpotifyRepository
import com.apm.trackify.provider.service.media.MediaServiceLifecycle
import com.apm.trackify.util.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Weird logic: dataset.toList()
 *
 * If you pass the same list to the adapter it does not even call the DiffUtil. The library
 * assumes you are using Room or any other ORM which offers a new async list every time it
 * gets updated, so just calling submitList on it will work, and for sloppy developers, it
 * prevents doing the calculations twice if the same list is called.
 */
@HiltViewModel
class PlaylistCreateViewModel @Inject constructor(
    private val mediaServiceLifecycle: MediaServiceLifecycle,
    spotifyRepository: SpotifyRepository,
) : ViewModel() {

    val error = MutableLiveData<Int>()
    val playlist = MutableLiveData<PlaylistItem>()
    val tracks = MutableLiveData<List<TrackItem>>()

    private var tracklist = mutableListOf<TrackItem>()

    init {
        playlist.value = MockProvider.playlist

        viewModelScope.launch {
            try {
                tracklist = spotifyRepository.generateTracklist().toMutableList()
                tracks.value = tracklist.toList()
            } catch (e: HttpException) {
                error.value = R.string.error
            } catch (e: IOException) {
                error.value = R.string.internet
            }
        }
    }

    fun move(from: Int, to: Int) {
        tracklist.swap(from, to)
        tracks.value = tracklist.toList()
    }

    fun remove(position: Int) {
        mediaServiceLifecycle.stop(position)
        tracklist.removeAt(position)
        tracks.value = tracklist.toList()
    }
}