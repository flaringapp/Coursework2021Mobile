package com.flaringapp.coursework2021.data.network

import android.util.Log
import com.flaringapp.coursework2021.app.Constants
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsApiService
import com.flaringapp.coursework2021.data.network.features.managers.ManagersApiService
import com.flaringapp.coursework2021.data.network.features.profile.ProfileApiService
import com.flaringapp.coursework2021.data.network.features.residents.ResidentsApiService
import com.flaringapp.coursework2021.data.network.features.rooms.RoomsApiService
import com.flaringapp.coursework2021.data.network.modifiers.ModifierApplyInterceptor
import com.flaringapp.coursework2021.data.network.modifiers.ParametrizedCallAdapterFactory
import com.flaringapp.coursework2021.data.network.modifiers.RequestDataCache
import com.flaringapp.coursework2021.data.network.modifiers.modifier.RequestModifier
import com.flaringapp.coursework2021.data.network.modifiers.modifier.RequestTokenAppender
import com.flaringapp.coursework2021.data.network.modifiers.setupModifiersCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitAdapter {

    companion object {
        private const val LOGGER_TAG = "Http"
    }

    val profileService: ProfileApiService = createClientAutoToken(NetworkConstants.profile)
    val buildingsService: BuildingsApiService = createClientAutoToken(NetworkConstants.buildings)
    val roomsService: RoomsApiService = createClientAutoToken(NetworkConstants.rooms)
    val managersService: ManagersApiService = createClientAutoToken(NetworkConstants.managers)
    val residentsService: ResidentsApiService = createClientAutoToken(NetworkConstants.residents)

    private inline fun <reified T> createClientAutoToken(prefix: String): T {
        return createClient(prefix, RequestTokenAppender())
    }

    private inline fun <reified T> createClient(
        prefix: String,
        vararg modifiers: RequestModifier
    ): T {
        return createRetrofitClient(prefix, modifiers.toList()).create(T::class.java)
    }

    private fun createRetrofitClient(
        prefix: String,
        modifiers: List<RequestModifier> = emptyList()
    ): Retrofit {
        val dataCache = RequestDataCache()
        return Retrofit.Builder()
            .client(createHttpClient(dataCache))
            .baseUrl(NetworkConstants.serverUrl + prefix)
            .addConverterFactory(MoshiConverterFactory.create())
            .withModifiers(dataCache, modifiers)
            .build()
            .setupModifiersCallAdapterFactory()
    }

    private fun createHttpClient(dataCache: RequestDataCache) = OkHttpClient.Builder()
        .connectTimeout(Constants.API_CALL_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.API_CALL_READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.API_CALL_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(ModifierApplyInterceptor(dataCache))
        .addInterceptor(HttpLoggingInterceptor { message ->
            Log.d(LOGGER_TAG, message)
        }.apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private fun Retrofit.Builder.withModifiers(
        dataCache: RequestDataCache,
        modifiers: List<RequestModifier>
    ) = apply {
        if (modifiers.isEmpty()) return@apply
        addCallAdapterFactory(
            createModifiersCallAdapterFactory(dataCache, modifiers)
        )
    }

    private fun createModifiersCallAdapterFactory(
        dataCache: RequestDataCache,
        modifiers: List<RequestModifier>
    ) = ParametrizedCallAdapterFactory(
        emptyList(),
        dataCache,
        modifiers.toSet()
    )
}