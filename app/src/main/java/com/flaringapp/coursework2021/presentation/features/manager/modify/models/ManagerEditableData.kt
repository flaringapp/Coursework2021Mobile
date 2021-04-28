package com.flaringapp.coursework2021.presentation.features.manager.modify.models

import com.flaringapp.coursework2021.data.repository.managers.models.Manager

class ManagerEditableData(
    var name: String = "",
    var surname: String = "",
    var email: String = "",
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
        manager?.id ?: Manager.NO_ID,
        name.trim(),
        surname.trim(),
        email.trim(),
        description.trim(),
        coworkingId!!,
        coworkingName!!
    )

}