package com.flaringapp.coursework2021.presentation.features.menu.model

import androidx.lifecycle.MutableLiveData
import com.flaringapp.coursework2021.data.repository.profile.ProfileRepository
import com.flaringapp.coursework2021.data.repository.profile.models.ManagerInfo
import com.flaringapp.coursework2021.data.repository.profile.models.UserType
import com.flaringapp.coursework2021.data.storage.DataStorage
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.EmptyMenuDisplay
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.MenuDisplay
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import org.koin.core.context.GlobalContext

class MenuModelImpl(
    private val profileRepository: ProfileRepository,
    private val dataStorage: DataStorage
) : MenuModel() {

    override val loginData = SingleLiveEvent<Unit>()

    override val displayData = MutableLiveData<MenuDisplay>()

    override val openRoomsData = SingleLiveEvent<String>()

    init {
        setup()
    }

    override fun openRooms() {
        openRoomsData.value = GlobalContext.get().get<ManagerInfo>().buildingId
    }

    override fun logout() {
        profileRepository.logout()
        loginData.value = Unit
    }

    private fun setup() {
        val isLoggedIn = dataStorage.token.isNotEmpty()
        if (!isLoggedIn) {
            loginData.value = Unit
            return
        }

        profileRepository.setup()

        val userType = GlobalContext.get().getOrNull<UserType>()
        val display = userType?.menuBehavior?.display ?: EmptyMenuDisplay()
        displayData.value = display
    }

}