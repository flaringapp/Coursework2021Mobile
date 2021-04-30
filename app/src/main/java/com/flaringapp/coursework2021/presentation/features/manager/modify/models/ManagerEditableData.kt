package com.flaringapp.coursework2021.presentation.features.manager.modify.models

import com.flaringapp.coursework2021.data.repository.managers.models.Manager

class ManagerEditableData(
    var name: String = "",
    var surname: String = "",
    var email: String = "",
    var password: String = "",
    var description: String = "",
    var coworkingId: String? = null,
    var coworkingName: String? = null,
) {

    constructor(manager: Manager) : this(
        manager.name,
        manager.surname,
        manager.email,
        manager.description,
        manager.buildingId,
        manager.buildingName
    )

    fun toManager(manager: Manager?) = Manager(
        id = manager?.id ?: Manager.NO_ID,
        name = name.trim(),
        surname = surname.trim(),
        email = email.trim(),
        password = password.trim().takeIf { it.isNotEmpty() },
        description = description.trim(),
        buildingId = coworkingId!!,
        buildingName = coworkingName!!
    )

}