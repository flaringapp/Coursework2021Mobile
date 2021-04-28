package com.flaringapp.coursework2021.presentation.features.building.modify.models

import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.repository.entity.models.GeoLocation

class BuildingEditableData(
    var name: String = "",
    var description: String = "",
    var latitude: String = "",
    var longitude: String = "",
    var address: String = "",
    var area: String = "",
) {

    constructor(building: Building) : this(
        building.name,
        building.description,
        building.location?.latitude?.toString() ?: "",
        building.location?.longitude?.toString() ?: "",
        building.address ?: "",
        building.area?.toString() ?: "",
    )

    fun toBuilding(building: Building?) = Building(
        building?.id ?: Building.NO_ID,
        name.trim(),
        description.trim(),
        createGeoLocation(),
        address.trim(),
        area.trim().toFloatOrNull()
    )

    private fun createGeoLocation(): GeoLocation? {
        return GeoLocation(
            latitude.trim().toDoubleOrNull() ?: return null,
            longitude.trim().toDoubleOrNull() ?: return null
        )
    }

}