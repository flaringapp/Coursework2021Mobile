package com.flaringapp.coursework2021.data.repository.entity

import com.flaringapp.coursework2021.app.common.getKey
import com.flaringapp.coursework2021.app.common.toInt
import com.flaringapp.coursework2021.data.network.features.buildings.request.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse
import com.flaringapp.coursework2021.data.network.features.rooms.request.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.repository.entity.models.GeoLocation
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.data.repository.entity.models.RoomType

val roomTypeKeys = mapOf(
    RoomType.OpenSpace to "open_space",
    RoomType.Private to "private",
)

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

fun Room.asRequest() = RoomRequest(
    id,
    buildingId,
    name,
    description,
    roomTypeKeys[type]!!,
    price,
    hasBoard?.toInt(),
    hasBalcony?.toInt(),
    workplacesCount,
    windowCount,
    area
)

fun RoomResponse.parseRoom() = Room(
    id,
    buildingId,
    name,
    description ?: "",
    roomTypeKeys.getKey(type)!!,
    price,
    hasBoard?.let { it == 1 },
    hasBalcony?.let { it == 1 },
    workplacesCount,
    windowCount,
    area
)