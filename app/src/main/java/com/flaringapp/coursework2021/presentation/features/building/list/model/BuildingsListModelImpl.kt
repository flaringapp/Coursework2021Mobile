package com.flaringapp.coursework2021.presentation.features.building.list.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.app.common.clearAndAdd
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.entity.EntityRepository
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.building.list.models.BuildingViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent

class BuildingsListModelImpl(
    private val repository: EntityRepository,
    private val textProvider: TextProvider,
): BuildingsListModel() {

    override val buildingsData = MutableLiveData<List<BuildingViewData>>()
    override val addBuildingData = SingleLiveEvent<BuildingViewData>()
    override val updateBuildingData = SingleLiveEvent<BuildingViewData>()
    override val deleteBuildingData = SingleLiveEvent<String>()

    override val openCreateBuildingData = SingleLiveEvent<Unit>()
    override val openEditBuildingData = SingleLiveEvent<Building>()

    private val buildings: MutableList<Building> = mutableListOf()

    init {
        loadBuildings()
    }

    override fun createNewBuilding() {
        openCreateBuildingData.value = Unit
    }

    override fun editBuilding(id: String) {
        val building = buildings.find { it.id == id } ?: return
        openEditBuildingData.value = building
    }

    private fun loadBuildings() {
        viewModelScope.launchOnIO {
            val loadedBuildings = safeCall { repository.getBuildings() } ?: return@launchOnIO

            buildings.clearAndAdd(loadedBuildings)

            val buildingsViewData = loadedBuildings.map {
                it.toViewData()
            }

            withMainContext {
                buildingsData.value = buildingsViewData
            }
        }
    }

    private fun Building.toViewData() = BuildingViewData(
        id,
        name,
        description,
        location?.let { textProvider.formatLocation(it) },
        address,
        area?.let { textProvider.formatArea(it) }
    )
}