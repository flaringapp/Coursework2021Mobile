package com.flaringapp.coursework2021.data.repository.profile

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.request.LoginRequest
import com.flaringapp.coursework2021.data.repository.profile.models.LoginData
import com.flaringapp.coursework2021.data.repository.profile.models.Profile
import com.flaringapp.coursework2021.data.repository.profile.models.ProfileCommonData
import com.flaringapp.coursework2021.data.storage.DataStorage

class ProfileRepositoryImpl(
    private val sourceModel: ProfileSourceModel,
    private val dataStorage: DataStorage
) : ProfileRepository {

    private var currentProfile: Profile? = null

    override suspend fun login(login: String, password: String): CallResult<LoginData> {
        return sourceModel.login(LoginRequest(login, password))
            .transform { parseLoginResponse() }
            .doOnSuccess {
                currentProfile = it.profile
                dataStorage.token = it.token
                dataStorage.userId = it.profile.id
                dataStorage.name = it.profile.name
                dataStorage.surname = it.profile.surname
                dataStorage.email = it.profile.email
                dataStorage.userType = it.userType.key
                dataStorage.buildingId = it.profile.commonData.buildingId ?: ""

                ProfileCache.setProfile(it.profile)
                ProfileCache.setUserType(it.userType)
                it.userType.setupDependencies(it.profile)
            }
    }

    override fun getCurrentProfile(): Profile? {
        return currentProfile
    }

    override fun setup() {
        val profile = Profile(
            dataStorage.userId,
            dataStorage.name,
            dataStorage.surname,
            dataStorage.email,
            ProfileCommonData(dataStorage.buildingId)
        )

        val userType = parseUserType(dataStorage.userType)
        ProfileCache.setProfile(profile)
        ProfileCache.setUserType(userType)
        userType.setupDependencies(profile)
    }

    override fun logout() {
        dataStorage.token = ""
        dataStorage.userId = ""
        dataStorage.userType = ""
        currentProfile = null
        ProfileCache.setProfile(null)
        ProfileCache.setUserType(null)
        ProfileCache.setManagerInfo(null)
    }
}