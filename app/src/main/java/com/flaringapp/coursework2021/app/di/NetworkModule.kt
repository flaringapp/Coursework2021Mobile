package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.network.RetrofitAdapter
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModel
import com.flaringapp.coursework2021.data.network.features.managers.ManagersSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.rents.RentsSourceModel
import com.flaringapp.coursework2021.data.network.features.residents.ResidentsSourceModel
import com.flaringapp.coursework2021.data.network.features.rooms.RoomsSourceModel
import com.flaringapp.coursework2021.data.network.features.transactions.TransactionsSourceModel
import com.flaringapp.coursework2021.data.network.features.transactions.TransactionsSourceModelImpl
import com.flaringapp.coursework2021.mock.*
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy { RetrofitAdapter() }

    single { adapter.profileService }
    single { adapter.buildingsService }
    single { adapter.roomsService }
    single { adapter.managersService }
    single { adapter.residentsService }
    single { adapter.rentsService }
    single { adapter.transactionsService }

//    single<ProfileSourceModel> { ProfileSourceModelImpl(get()) }
//    single<BuildingsSourceModel> { BuildingsSourceModelImpl(get()) }
//    single<RoomsSourceModel> { RoomsSourceModelImpl() }
//    single<ManagersSourceModel> { ManagersSourceModelImpl(get()) }
//    single<ResidentsSourceModel> { ResidentsSourceModelImpl(get()) }
//    single<RentsSourceModel> { RentsSourceModelImpl(get()) }
    single<TransactionsSourceModel> { TransactionsSourceModelImpl(get()) }

    single<ProfileSourceModel> { ProfileSourceModelMock() }
    single<BuildingsSourceModel> { BuildingsSourceModelMock() }
    single<RoomsSourceModel> { RoomsSourceModelMock() }
    single<ManagersSourceModel> { ManagersSourceModelMock() }
    single<ResidentsSourceModel> { ResidentsSourceModelMock() }
    single<RentsSourceModel> { RentsSourceModelMock() }

}