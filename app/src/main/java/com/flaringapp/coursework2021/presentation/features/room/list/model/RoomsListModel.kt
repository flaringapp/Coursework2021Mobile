package com.flaringapp.coursework2021.presentation.features.room.list.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.room.list.models.RoomViewData

abstract class RoomsListModel: BaseViewModel() {

    abstract val roomsData: LiveData<List<RoomViewData>>
    abstract val addRoomData: LiveData<RoomViewData>
    abstract val updateRoomData: LiveData<RoomViewData>
    abstract val deleteRoomData: LiveData<String>

    abstract val openRoomData: LiveData<Room>

    abstract val openRoomActionsData: LiveData<Unit>

    abstract val openConfirmDeleteRoomData: LiveData<String>

    abstract val openCreateRoomData: LiveData<String>
    abstract val openEditRoomData: LiveData<Room>

    abstract fun initBuildingId(id: String)

    abstract fun loadRooms()

    abstract fun createNewRoom()

    abstract fun openRoom(id: String)

    abstract fun handleRoomOptions(id: String)

    abstract fun editSelectedRoom()

    abstract fun deleteSelectedRoom()
    abstract fun confirmDeleteSelectedRoom()

}