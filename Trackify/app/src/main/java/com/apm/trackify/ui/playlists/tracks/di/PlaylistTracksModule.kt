package com.apm.trackify.ui.playlists.tracks.di

import androidx.lifecycle.SavedStateHandle
import com.apm.trackify.model.domain.Playlist
import com.apm.trackify.ui.playlists.tracks.view.model.PlaylistTracksViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PlaylistTracksModule {

    @Provides
    fun providePlaylist(stateHandle: SavedStateHandle): Playlist =
        stateHandle.get<Playlist>("playlist")
            ?: throw IllegalStateException("playlist not found in state handle")

    @Provides
    fun provideViewModel(playlist: Playlist): PlaylistTracksViewModel =
        PlaylistTracksViewModel(playlist)
}