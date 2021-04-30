package com.flaringapp.coursework2021.data.repository.profile.models

import com.flaringapp.coursework2021.presentation.features.menu.behaviour.MenuBehaviour
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.SimpleMenuBehaviour
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.AdminMenuDisplay

class UserTypeAdmin: UserType {

    override val menuBehavior: MenuBehaviour = SimpleMenuBehaviour(
        AdminMenuDisplay()
    )

    override fun setupDependencies(profile: Profile) {
    }
}