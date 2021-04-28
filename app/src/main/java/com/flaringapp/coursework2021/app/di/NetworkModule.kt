package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.network.RetrofitAdapter
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModel
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModelImpl
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModelImpl
import com.flaringapp.coursework2021.mock.BuildingsSourceModelMock
import com.flaringapp.coursework2021.mock.ProfileSourceModelMock
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy { RetrofitAdapter() }

    single { adapter.profileService }
    single { adapter.buildingsService }

//    single<ProfileSourceModel> { ProfileSourceModelImpl(get()) }
//    single<BuildingsSourceModel> { BuildingsSourceModelImpl(get()) }

    single<ProfileSourceModel> { ProfileSourceModelMock() }
    single<BuildingsSourceModel> { BuildingsSourceModelMock() }

}