package com.flaringapp.coursework2021.presentation.features.building.modify.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.isCorrectDouble
import com.flaringapp.coursework2021.app.common.isCorrectFloat
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.presentation.features.building.modify.behaviour.ModifyBuildingBehaviour
import com.flaringapp.coursework2021.presentation.features.building.modify.models.BuildingEditableData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.startLoadingTask

class ModifyBuildingModelImpl : ModifyBuildingModel() {

    override val nameData = SingleLiveEvent<CharSequence>()
    override val descriptionData = SingleLiveEvent<CharSequence>()
    override val latitudeData = SingleLiveEvent<CharSequence>()
    override val longitudeData = SingleLiveEvent<CharSequence>()
    override val addressData = SingleLiveEvent<CharSequence>()
    override val areaData = SingleLiveEvent<CharSequence>()

    override val nameErrorData = SingleLiveEvent<Int?>()
    override val latitudeErrorData = SingleLiveEvent<Int?>()
    override val longitudeErrorData = SingleLiveEvent<Int?>()
    override val areaErrorData = SingleLiveEvent<Int?>()

    override val loadingData = MutableLiveData(false)

    override val closeScreenData = SingleLiveEvent<Unit>()

    private lateinit var behaviour: ModifyBuildingBehaviour

    private var sourceBuilding: Building? = null
    private lateinit var editor: BuildingEditableData

    override fun initBehaviour(behaviour: ModifyBuildingBehaviour) {
        this.behaviour = behaviour
        sourceBuilding = behaviour.building
        editor = behaviour.preliminaryData ?: BuildingEditableData()
        editor.setup()
    }

    override fun handleNameChanged(name: String) {
        editor.name = name
        nameErrorData.value = null
    }

    override fun handleDescriptionChanged(description: String) {
        editor.description = description
    }

    override fun handleLatitudeChanged(latitude: String) {
        editor.latitude = latitude
        latitudeErrorData.value = null
    }

    override fun handleLongitudeChanged(longitude: String) {
        editor.longitude = longitude
        longitudeErrorData.value = null
    }

    override fun handleAddressChanged(address: String) {
        editor.address = address
    }

    override fun handleAreaChanged(area: String) {
        editor.area = area
        areaErrorData.value = null
    }

    override fun saveBuilding() {
        if (!isValid()) return
        performSaveBuilding()
    }

    private fun BuildingEditableData.setup() {
        nameData.value = name
        descriptionData.value = description
        latitudeData.value = latitude
        longitudeData.value = longitude
        addressData.value = address
        areaData.value = area
    }

    private fun performSaveBuilding() {
        viewModelScope.startLoadingTask(loadingData) {
            val building = editor.toBuilding(sourceBuilding)
            safeCall { behaviour.modifyBuilding(building) }
                ?: return@startLoadingTask

            withMainContext {
                closeScreenData.value = Unit
            }
        }
    }

    private fun isValid(): Boolean {
        when {
            editor.name.trim().isEmpty() -> {
                nameErrorData.value = R.string.error_building_empty_name
            }
            !editor.latitude.trim().isCorrectDouble() -> {
                latitudeErrorData.value = R.string.error_building_invalid_latitude
            }
            !editor.longitude.trim().isCorrectDouble() -> {
                longitudeErrorData.value = R.string.error_building_invalid_longitude
            }
            !editor.area.trim().isCorrectFloat() -> {
                areaErrorData.value = R.string.error_building_invalid_area
            }
            else -> return true
        }
        return false
    }
}