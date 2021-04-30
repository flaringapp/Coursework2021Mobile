package com.flaringapp.coursework2021.data.repository.profile.models

import com.flaringapp.coursework2021.data.repository.profile.ProfileCache
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.MenuBehaviour
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.SimpleMenuBehaviour
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.ManagerMenuDisplay

class UserTypeManager: UserType {

    override val menuBehavior: MenuBehaviour = SimpleMenuBehaviour(
        ManagerMenuDisplay()
    )

    override fun setupDependencies(profile: Profile) {
        ProfileCache.setManagerInfo(
            ManagerInfo(profile.commonData.buildingId!!)
        )
    }
}