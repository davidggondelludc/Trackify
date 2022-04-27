package com.apm.trackify.service.spotify.interceptor

import com.apm.trackify.ui.main.MainApplication
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${MainApplication.TOKEN}")
                .build()
        )
}