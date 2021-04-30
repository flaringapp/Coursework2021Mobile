package com.flaringapp.coursework2021.data.network.modifiers

import okhttp3.Interceptor
import okhttp3.Response

class ModifierApplyInterceptor(
    private val dataCache: RequestDataCache
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val requestBuilder = request.newBuilder()
            .addHeader("Content-Type", "application/json")

        val modifier = dataCache.resolveRequestModifier(request)
        modifier?.applyChanges(requestBuilder)

        request = requestBuilder.build()

        return chain.proceed(request)
    }

}