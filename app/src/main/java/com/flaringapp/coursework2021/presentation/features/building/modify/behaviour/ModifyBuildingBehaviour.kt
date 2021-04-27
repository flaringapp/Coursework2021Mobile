package com.flaringapp.coursework2021.presentation.features.building.modify.behaviour

import android.os.Parcelable
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.presentation.features.building.modify.models.BuildingEditableData

interface ModifyBuildingBehaviour: Parcelable {

    val building: Building?
    val preliminaryData: BuildingEditableData?

    suspend fun modifyBuilding(building: Building): CallResultNothing

}