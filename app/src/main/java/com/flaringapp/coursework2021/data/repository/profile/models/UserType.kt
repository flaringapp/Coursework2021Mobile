package com.flaringapp.coursework2021.data.repository.profile.models

import com.flaringapp.coursework2021.presentation.features.menu.behaviour.MenuBehaviour

interface UserType {

    val menuBehavior: MenuBehaviour

    fun setupDependencies(profile: Profile)

}