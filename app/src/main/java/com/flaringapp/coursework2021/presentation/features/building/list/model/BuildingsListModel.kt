package com.flaringapp.coursework2021.presentation.features.building.list.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.building.list.models.BuildingViewData

abstract class BuildingsListModel: BaseViewModel() {

    abstract val buildingsData: LiveData<List<BuildingViewData>>
    abstract val addBuildingData: LiveData<BuildingViewData>
    abstract val updateBuildingData: LiveData<BuildingViewData>
    abstract val deleteBuildingData: LiveData<String>

    abstract val openCreateBuildingData: LiveData<Unit>
    abstract val openEditBuildingData: LiveData<Building>
    
    abstract fun createNewBuilding()
    abstract fun editBuilding(id: String)

}