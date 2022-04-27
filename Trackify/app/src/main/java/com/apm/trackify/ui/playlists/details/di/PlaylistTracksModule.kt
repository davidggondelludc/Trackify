package com.apm.trackify.ui.playlists.details.di

import androidx.lifecycle.SavedStateHandle
import com.apm.trackify.model.domain.PlaylistItem
import com.apm.trackify.ui.playlists.details.view.model.PlaylistTracksViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PlaylistTracksModule {

    @Provides
    fun providePlaylist(stateHandle: SavedStateHandle): PlaylistItem =
        stateHandle.get<PlaylistItem>("playlist")
            ?: throw IllegalStateException("Playlist not found in state handle")

    @Provides
    fun provideViewModel(playlistItem: PlaylistItem): PlaylistTracksViewModel =
        PlaylistTracksViewModel(playlistItem)
}