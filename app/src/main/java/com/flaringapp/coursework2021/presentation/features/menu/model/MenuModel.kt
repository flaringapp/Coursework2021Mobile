package com.flaringapp.coursework2021.presentation.features.menu.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.MenuDisplay

abstract class MenuModel: BaseViewModel() {

    abstract val loginData: LiveData<Unit>

    abstract val displayData: LiveData<MenuDisplay>

    abstract val openRoomsData: LiveData<String>

    abstract fun openRooms()

    abstract fun logout()

}