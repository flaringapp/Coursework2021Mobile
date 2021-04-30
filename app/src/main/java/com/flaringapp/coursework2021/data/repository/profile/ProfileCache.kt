package com.flaringapp.coursework2021.data.repository.profile

import com.flaringapp.coursework2021.data.repository.profile.models.ManagerInfo
import com.flaringapp.coursework2021.data.repository.profile.models.Profile
import com.flaringapp.coursework2021.data.repository.profile.models.UserType

object ProfileCache {

    private var mProfile: Profile? = null
    val profile: Profile
        get() = mProfile!!

    private var mUserType: UserType? = null
    val userType: UserType
        get() = mUserType!!

    var managerInfo: ManagerInfo? = null
        private set

    fun setProfile(profile: Profile) {
        this.mProfile = profile
    }

    fun setUserType(userType: UserType) {
        this.mUserType = userType
    }

    fun setManagerInfo(managerInfo: ManagerInfo) {
        this.managerInfo = managerInfo
    }

}