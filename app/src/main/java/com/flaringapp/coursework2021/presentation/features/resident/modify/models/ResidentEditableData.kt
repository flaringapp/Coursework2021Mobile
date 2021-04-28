package com.flaringapp.coursework2021.presentation.features.resident.modify.models

import com.flaringapp.coursework2021.data.repository.residents.models.Resident

class ResidentEditableData(
    var name: String = "",
    var surname: String = "",
    var email: String = "",
    var description: String = "",
) {

    constructor(resident: Resident) : this(
        resident.name,
        resident.surname,
        resident.email,
        resident.description,
    )

    fun toResident(resident: Resident?, buildingId: String?) = Resident(
        resident?.id ?: Resident.NO_ID,
        name.trim(),
        surname.trim(),
        email.trim(),
        description.trim(),
        buildingId,
        null
    )

}