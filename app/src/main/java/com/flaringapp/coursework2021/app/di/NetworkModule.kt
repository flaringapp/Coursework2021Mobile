package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.network.RetrofitAdapter
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.rooms.RoomsSourceModel
import com.flaringapp.coursework2021.mock.BuildingsSourceModelMock
import com.flaringapp.coursework2021.mock.ProfileSourceModelMock
import com.flaringapp.coursework2021.mock.RoomsSourceModelMock
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy { RetrofitAdapter() }

    single { adapter.profileService }
    single { adapter.buildingsService }
    single { adapter.roomsService }

//    single<ProfileSourceModel> { ProfileSourceModelImpl(get()) }
//    single<BuildingsSourceModel> { BuildingsSourceModelImpl(get()) }
//    single<RoomsSourceModel> { RoomsSourceModelImpl() }

    single<ProfileSourceModel> { ProfileSourceModelMock() }
    single<BuildingsSourceModel> { BuildingsSourceModelMock() }
    single<RoomsSourceModel> { RoomsSourceModelMock() }

}