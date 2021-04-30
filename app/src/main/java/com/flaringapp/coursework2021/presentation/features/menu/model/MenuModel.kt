package com.flaringapp.coursework2021.presentation.features.menu.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.MenuDisplay

abstract class MenuModel: BaseViewModel() {

    abstract val displayModel: LiveData<MenuDisplay>

}