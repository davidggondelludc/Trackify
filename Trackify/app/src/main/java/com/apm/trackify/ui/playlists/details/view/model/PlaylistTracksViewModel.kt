package com.apm.trackify.ui.playlists.details.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apm.trackify.R
import com.apm.trackify.provider.model.domain.PlaylistItem
import com.apm.trackify.provider.model.domain.TrackItem
import com.apm.trackify.provider.repository.SpotifyRepository
import com.apm.trackify.ui.playlists.details.view.model.enum.SortType
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
class PlaylistTracksViewModel @Inject constructor(
    playlistItem: PlaylistItem,
    spotifyRepository: SpotifyRepository
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Int>()
    val tracks = MutableLiveData<List<TrackItem>>()
    var sortType: SortType = SortType.CUSTOM

    private var ascending: Boolean = true
    private lateinit var originalTracklist: List<TrackItem>
    private lateinit var tracklist: MutableList<TrackItem>

    init {
        loading.value = true
        viewModelScope.launch {
            try {
                originalTracklist = spotifyRepository.getPlaylistTracks(playlistItem.id)
                sort(null, null)
                loading.value = false
            } catch (e: HttpException) {
                error.value = R.string.error
            } catch (e: IOException) {
                error.value = R.string.internet
            }
        }
    }

    fun sort(sortType: SortType?, ascending: Boolean?) {
        if (sortType != null) {
            this.sortType = sortType
        }
        if (ascending != null) {
            this.ascending = ascending
        }

        when (this.sortType) {
            SortType.TITLE -> if (this.ascending) {
                tracklist.sortBy { it.name }
            } else {
                tracklist.sortByDescending { it.name }
            }
            SortType.ARTIST -> if (this.ascending) {
                tracklist.sortBy { it.artists }
            } else {
                tracklist.sortByDescending { it.artists }
            }
            SortType.DURATION -> if (this.ascending) {
                tracklist.sortBy { it.duration }
            } else {
                tracklist.sortByDescending { it.duration }
            }
            SortType.RECENTLY_ADDED -> if (this.ascending) {
                tracklist.sortBy { it.addedAt }
            } else {
                tracklist.sortByDescending { it.addedAt }
            }
            SortType.CUSTOM -> if (this.ascending) {
                tracklist = originalTracklist.toMutableList()
            } else {
                tracklist = originalTracklist.toMutableList()
                tracklist.reverse()
            }
        }
        tracks.value = tracklist.toList()
    }
}