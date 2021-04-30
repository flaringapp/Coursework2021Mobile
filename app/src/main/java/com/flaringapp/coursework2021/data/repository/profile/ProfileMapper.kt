package com.flaringapp.coursework2021.data.repository.profile

import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse
import com.flaringapp.coursework2021.data.repository.profile.models.*

val userTypeDefinitions = mapOf(
    "admin" to UserTypeAdmin(),
    "manager" to UserTypeManager()
)

fun LoginResponse.parseLoginResponse(): LoginData {
    return LoginData(
        Profile(id, name, surname, email, parseProfileCommonData()),
        parseUserType(userType),
        token,
    )
}

fun LoginResponse.parseProfileCommonData() = ProfileCommonData(buildingId)

fun parseUserType(userType: String): UserType {
    return userTypeDefinitions[userType]!!
}