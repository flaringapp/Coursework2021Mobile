package com.flaringapp.coursework2021.data.repository.entity.storage

import com.flaringapp.coursework2021.data.repository.entity.models.Building
import kotlinx.coroutines.flow.Flow

interface BuildingsStorage {

    val addBuildingFlow: Flow<Building>
    val editBuildingFlow: Flow<Building>
    val deleteBuildingFlow: Flow<String>

}