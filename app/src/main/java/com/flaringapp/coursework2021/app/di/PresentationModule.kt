package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.presentation.features.building.list.model.BuildingsListModel
import com.flaringapp.coursework2021.presentation.features.building.list.model.BuildingsListModelImpl
import com.flaringapp.coursework2021.presentation.features.building.modify.model.ModifyBuildingModel
import com.flaringapp.coursework2021.presentation.features.building.modify.model.ModifyBuildingModelImpl
import com.flaringapp.coursework2021.presentation.features.login.viewmodel.LoginModel
import com.flaringapp.coursework2021.presentation.features.login.viewmodel.LoginModelImpl
import com.flaringapp.coursework2021.presentation.features.manager.list.model.ManagersListModel
import com.flaringapp.coursework2021.presentation.features.manager.list.model.ManagersListModelImpl
import com.flaringapp.coursework2021.presentation.features.manager.modify.model.ModifyManagerModel
import com.flaringapp.coursework2021.presentation.features.manager.modify.model.ModifyManagerModelImpl
import com.flaringapp.coursework2021.presentation.features.menu.model.MenuModel
import com.flaringapp.coursework2021.presentation.features.menu.model.MenuModelImpl
import com.flaringapp.coursework2021.presentation.features.resident.list.model.ResidentsListModel
import com.flaringapp.coursework2021.presentation.features.resident.list.model.ResidentsListModelImpl
import com.flaringapp.coursework2021.presentation.features.resident.modify.model.ModifyResidentModel
import com.flaringapp.coursework2021.presentation.features.resident.modify.model.ModifyResidentModelImpl
import com.flaringapp.coursework2021.presentation.features.room.list.model.RoomsListModel
import com.flaringapp.coursework2021.presentation.features.room.list.model.RoomsListModelImpl
import com.flaringapp.coursework2021.presentation.features.room.modify.model.ModifyRoomModel
import com.flaringapp.coursework2021.presentation.features.room.modify.model.ModifyRoomModelImpl
import com.flaringapp.coursework2021.presentation.features.searchresident.model.SearchResidentModel
import com.flaringapp.coursework2021.presentation.features.searchresident.model.SearchResidentModelImpl
import com.flaringapp.coursework2021.presentation.features.tenants.model.TenantsModel
import com.flaringapp.coursework2021.presentation.features.tenants.model.TenantsModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel<LoginModel> { LoginModelImpl(get()) }

    viewModel<MenuModel> { MenuModelImpl() }

    viewModel<BuildingsListModel> { BuildingsListModelImpl(get(), get(), get()) }

    viewModel<ModifyBuildingModel> { ModifyBuildingModelImpl() }

    viewModel<RoomsListModel> { RoomsListModelImpl(get(), get(), get()) }

    viewModel<ModifyRoomModel> { ModifyRoomModelImpl(get()) }

    viewModel<ManagersListModel> { ManagersListModelImpl(get(), get(), get()) }

    viewModel<ModifyManagerModel> { ModifyManagerModelImpl(get()) }

    viewModel<ResidentsListModel> { ResidentsListModelImpl(get(), get(), get()) }

    viewModel<ModifyResidentModel> { ModifyResidentModelImpl() }

    viewModel<TenantsModel> { TenantsModelImpl(get(), get()) }

    viewModel<SearchResidentModel> { SearchResidentModelImpl(get(), get()) }

}