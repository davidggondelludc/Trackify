package com.apm.trackify.service.spotify.di

import com.apm.trackify.service.spotify.SpotifyService
import com.apm.trackify.service.spotify.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpotifyModule {

    @Provides
    @Singleton
    fun provideSpotifyService(): SpotifyService =
        Retrofit
            .Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(HeaderInterceptor())
                    .build()
            )
            .build()
            .create(SpotifyService::class.java)
}