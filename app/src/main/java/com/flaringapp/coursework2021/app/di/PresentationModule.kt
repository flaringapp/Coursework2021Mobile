package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.presentation.features.building.list.model.BuildingsListModel
import com.flaringapp.coursework2021.presentation.features.building.list.model.BuildingsListModelImpl
import com.flaringapp.coursework2021.presentation.features.building.modify.model.ModifyBuildingModel
import com.flaringapp.coursework2021.presentation.features.building.modify.model.ModifyBuildingModelImpl
import com.flaringapp.coursework2021.presentation.features.login.viewmodel.LoginModel
import com.flaringapp.coursework2021.presentation.features.login.viewmodel.LoginModelImpl
import com.flaringapp.coursework2021.presentation.features.managers.list.model.ManagersListModel
import com.flaringapp.coursework2021.presentation.features.managers.list.model.ManagersListModelImpl
import com.flaringapp.coursework2021.presentation.features.menu.model.MenuModel
import com.flaringapp.coursework2021.presentation.features.menu.model.MenuModelImpl
import com.flaringapp.coursework2021.presentation.features.room.list.model.RoomsListModel
import com.flaringapp.coursework2021.presentation.features.room.list.model.RoomsListModelImpl
import com.flaringapp.coursework2021.presentation.features.room.modify.model.ModifyRoomModel
import com.flaringapp.coursework2021.presentation.features.room.modify.model.ModifyRoomModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel<LoginModel> { LoginModelImpl(get()) }

    viewModel<MenuModel> { MenuModelImpl() }

    viewModel<BuildingsListModel> { BuildingsListModelImpl(get(), get(), get()) }

    viewModel<ModifyBuildingModel> { ModifyBuildingModelImpl() }

    viewModel<RoomsListModel> { RoomsListModelImpl(get(), get(), get()) }

    viewModel<ModifyRoomModel> { ModifyRoomModelImpl() }

    viewModel<ManagersListModel> { ManagersListModelImpl(get(), get(), get()) }

}