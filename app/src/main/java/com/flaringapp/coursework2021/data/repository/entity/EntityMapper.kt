package com.flaringapp.coursework2021.data.repository.entity

import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.repository.entity.models.GeoLocation

fun Building.asRequest() = BuildingRequest(
    id, name, description, location?.latitude, location?.longitude, address, area
)

fun BuildingsResponse.parseBuilding() = Building(
    id,
    name,
    description ?: "",
    parseLocation(),
    address,
    area
)

fun BuildingsResponse.parseLocation(): GeoLocation? {
    return GeoLocation(
        lat ?: return null,
        lon ?: return null
    )
}