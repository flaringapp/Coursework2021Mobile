package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.network.RetrofitAdapter
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModel
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModelImpl
import com.flaringapp.coursework2021.data.network.features.managers.ManagersSourceModel
import com.flaringapp.coursework2021.data.network.features.managers.ManagersSourceModelImpl
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModelImpl
import com.flaringapp.coursework2021.data.network.features.rentals.RentalsSourceModel
import com.flaringapp.coursework2021.data.network.features.rentals.RentalsSourceModelImpl
import com.flaringapp.coursework2021.data.network.features.residents.ResidentsSourceModel
import com.flaringapp.coursework2021.data.network.features.residents.ResidentsSourceModelImpl
import com.flaringapp.coursework2021.data.network.features.rooms.RoomsSourceModel
import com.flaringapp.coursework2021.data.network.features.rooms.RoomsSourceModelImpl
import com.flaringapp.coursework2021.data.network.features.transactions.TransactionsSourceModel
import com.flaringapp.coursework2021.data.network.features.transactions.TransactionsSourceModelImpl
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy { RetrofitAdapter() }

    single { adapter.profileService }
    single { adapter.buildingsService }
    single { adapter.roomsService }
    single { adapter.managersService }
    single { adapter.residentsService }
    single { adapter.rentalsService }
    single { adapter.transactionsService }

    single<ProfileSourceModel> { ProfileSourceModelImpl(get()) }
    single<BuildingsSourceModel> { BuildingsSourceModelImpl(get()) }
    single<RoomsSourceModel> { RoomsSourceModelImpl(get()) }
    single<ManagersSourceModel> { ManagersSourceModelImpl(get()) }
    single<ResidentsSourceModel> { ResidentsSourceModelImpl(get()) }
    single<RentalsSourceModel> { RentalsSourceModelImpl(get()) }
    single<TransactionsSourceModel> { TransactionsSourceModelImpl(get()) }

//    single<ProfileSourceModel> { ProfileSourceModelMock() }
//    single<BuildingsSourceModel> { BuildingsSourceModelMock() }
//    single<RoomsSourceModel> { RoomsSourceModelMock() }
//    single<ManagersSourceModel> { ManagersSourceModelMock() }
//    single<ResidentsSourceModel> { ResidentsSourceModelMock() }
//    single<RentalsSourceModel> { RentalsSourceModelMock() }

}