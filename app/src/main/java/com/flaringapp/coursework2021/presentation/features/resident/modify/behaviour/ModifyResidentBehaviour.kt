package com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour

import android.os.Parcelable
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.presentation.features.resident.modify.models.ResidentEditableData

interface ModifyResidentBehaviour: Parcelable {

    val resident: Resident?
    val preliminaryData: ResidentEditableData?

    suspend fun modifyResident(resident: Resident): CallResultNothing

}