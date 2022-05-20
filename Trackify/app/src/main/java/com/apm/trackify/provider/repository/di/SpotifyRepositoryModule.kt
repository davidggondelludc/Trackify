package com.apm.trackify.provider.repository.di

import com.apm.trackify.provider.repository.SpotifyRepository
import com.apm.trackify.provider.repository.SpotifyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SpotifyRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSpotifyRepository(spotifyRepositoryImpl: SpotifyRepositoryImpl): SpotifyRepository
}