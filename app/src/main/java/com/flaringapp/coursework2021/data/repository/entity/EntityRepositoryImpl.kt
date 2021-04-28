package com.flaringapp.coursework2021.data.repository.entity

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.common.call.transformList
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModel
import com.flaringapp.coursework2021.data.network.features.rooms.RoomsSourceModel
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.data.repository.entity.storage.BuildingsStorage
import com.flaringapp.coursework2021.data.repository.entity.storage.RoomsStorage
import kotlinx.coroutines.flow.MutableSharedFlow

class EntityRepositoryImpl(
    private val buildingsSourceModel: BuildingsSourceModel,
    private val roomsSourceModel: RoomsSourceModel
): EntityRepository, BuildingsStorage, RoomsStorage {

    override val addBuildingFlow = MutableSharedFlow<Building>()
    override val editBuildingFlow = MutableSharedFlow<Building>()
    override val deleteBuildingFlow = MutableSharedFlow<String>()

    override val addRoomFlow = MutableSharedFlow<Room>()
    override val editRoomFlow = MutableSharedFlow<Room>()
    override val deleteRoomFlow = MutableSharedFlow<String>()

    override suspend fun getBuildings(): CallResultList<Building> {
        return buildingsSourceModel.getBuildings()
            .transformList { parseBuilding() }
    }

    override suspend fun addBuilding(building: Building): CallResult<Building> {
        return buildingsSourceModel.addBuilding(building.asRequest())
            .transform { parseBuilding() }
            .doOnSuccessSuspend { addBuildingFlow.emit(it) }
    }

    override suspend fun editBuilding(building: Building): CallResult<Building> {
        return buildingsSourceModel.editBuilding(building.asRequest())
            .transform { parseBuilding() }
            .doOnSuccessSuspend { editBuildingFlow.emit(it) }
    }

    override suspend fun deleteBuilding(id: String): CallResultNothing {
        return buildingsSourceModel.deleteBuilding(id)
            .doOnSuccessSuspend { deleteBuildingFlow.emit(id) }
    }

    override suspend fun getRooms(buildingId: String): CallResultList<Room> {
        return roomsSourceModel.getRooms()
            .transformList { parseRoom() }
    }

    override suspend fun addRoom(room: Room): CallResult<Room> {
        return roomsSourceModel.addRoom(room.asRequest())
            .transform { parseRoom() }
            .doOnSuccessSuspend { addRoomFlow.emit(it) }
    }

    override suspend fun editRoom(room: Room): CallResult<Room> {
        return roomsSourceModel.editRoom(room.asRequest())
            .transform { parseRoom() }
            .doOnSuccessSuspend { editRoomFlow.emit(it) }
    }

    override suspend fun deleteRoom(id: String): CallResultNothing {
        return roomsSourceModel.deleteRoom(id)
            .doOnSuccessSuspend { deleteRoomFlow.emit(id) }
    }
}