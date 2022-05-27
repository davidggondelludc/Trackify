package com.apm.trackify.provider.service.spotify.interceptor

import com.apm.trackify.ui.main.MainApplication
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${MainApplication.TOKEN}")
                .build()
        )
}