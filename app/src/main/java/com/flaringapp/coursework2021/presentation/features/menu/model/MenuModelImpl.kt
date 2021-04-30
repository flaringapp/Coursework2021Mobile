package com.flaringapp.coursework2021.presentation.features.menu.model

import androidx.lifecycle.MutableLiveData
import com.flaringapp.coursework2021.data.repository.profile.models.UserType
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.EmptyMenuDisplay

class MenuModelImpl(
    userType: UserType?
) : MenuModel() {

    override val displayModel =
        MutableLiveData(userType?.menuBehavior?.display ?: EmptyMenuDisplay())

}