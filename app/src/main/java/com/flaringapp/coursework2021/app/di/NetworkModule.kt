package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.network.RetrofitAdapter
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModelImpl
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy { RetrofitAdapter() }

    single { adapter.profileService }

    single<ProfileSourceModel> { ProfileSourceModelImpl(get()) }

}