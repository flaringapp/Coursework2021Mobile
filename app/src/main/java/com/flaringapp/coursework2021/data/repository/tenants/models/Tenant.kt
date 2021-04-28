package com.flaringapp.coursework2021.data.repository.tenants.models

import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.text.TextProvider

class Tenant(
    val residentId: String,
    val residentName: String,
    val residentSurname: String,
) {

    constructor(resident: Resident) : this(
        resident.id,
        resident.name,
        resident.surname
    )

    fun formatNameSurname(textProvider: TextProvider): CharSequence {
        return textProvider.formatNameSurname(residentName, residentName)
    }
}