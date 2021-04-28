package com.flaringapp.coursework2021.presentation.features.room.list.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.*
import com.flaringapp.coursework2021.data.repository.entity.EntityRepository
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.data.repository.entity.storage.RoomsStorage
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.room.list.models.RoomViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.valueIfHasObservers

class RoomsListModelImpl(
    private val repository: EntityRepository,
    roomsStorage: RoomsStorage,
    private val textProvider: TextProvider,
) : RoomsListModel() {

    override val roomsData = MutableLiveData<List<RoomViewData>>()
    override val addRoomData = SingleLiveEvent<RoomViewData>()
    override val updateRoomData = SingleLiveEvent<RoomViewData>()
    override val deleteRoomData = SingleLiveEvent<String>()

    override val openRoomActionsData = SingleLiveEvent<Unit>()

    override val openConfirmDeleteRoomData = SingleLiveEvent<String>()

    override val openCreateRoomData = SingleLiveEvent<String>()
    override val openEditRoomData = SingleLiveEvent<Room>()

    private lateinit var buildingId: String

    private val rooms: MutableList<Room> = mutableListOf()

    private var pendingOptionsRoomId: String? = null

    init {
        // TODO refactor general list validation
        roomsStorage.addRoomFlow.collectOn(viewModelScope) { addedRoom ->
            rooms.add(addedRoom)
            addRoomData.valueIfHasObservers = addedRoom.toViewData()

            roomsData.value = rooms.map { it.toViewData() }
        }
        roomsStorage.editRoomFlow.collectOn(viewModelScope) { changedRoom ->
            rooms.replaceFirst(changedRoom) { it.id == changedRoom.id }
            updateRoomData.valueIfHasObservers = changedRoom.toViewData()

            roomsData.value = rooms.map { it.toViewData() }
        }
    }

    override fun initBuildingId(id: String) {
        this.buildingId = id
    }

    override fun loadRooms() {
        loadRoomsImpl()
    }

    override fun createNewRoom() {
        openCreateRoomData.value = buildingId
    }

    override fun handleRoomOptions(id: String) {
        pendingOptionsRoomId = id
        openRoomActionsData.value = Unit
    }

    override fun editSelectedRoom() {
        pendingRoomAction(this::editRoom)
    }

    override fun deleteSelectedRoom() {
        val roomId = pendingOptionsRoomId ?: return
        val room = rooms.find { it.id == roomId } ?: return
        openConfirmDeleteRoomData.value = room.name
    }

    override fun confirmDeleteSelectedRoom() {
        pendingRoomAction(this::deleteRoom)
    }

    private fun loadRoomsImpl() {
        viewModelScope.launchOnIO {
            val loadedRooms = safeCall { repository.getRooms(buildingId) } ?: return@launchOnIO

            rooms.clearAndAdd(loadedRooms)

            withMainContext {
                val roomsViewData = loadedRooms.map { it.toViewData() }
                roomsData.value = roomsViewData
            }
        }
    }

    private fun editRoom(id: String) {
        val room = rooms.find { it.id == id } ?: return
        openEditRoomData.value = room
    }

    private fun deleteRoom(id: String) {
        viewModelScope.launchOnIO {
            safeCall { repository.deleteRoom(id) } ?: return@launchOnIO

            withMainContext {
                deleteRoomData.value = id
            }
        }
    }

    private fun Room.toViewData() = RoomViewData(
        id,
        name,
        description,
        hasBoard,
        hasBalcony,
        textProvider.getPluralText(R.plurals.workplaces, workplacesCount ?: 0),
        textProvider.getPluralText(R.plurals.windows, windowCount ?: 0),
        area?.let { textProvider.formatArea(it) },
        type.formatName(textProvider)
    )

    private fun pendingRoomAction(action: (String) -> Unit) {
        val id = pendingOptionsRoomId ?: return
        action(id)
        pendingOptionsRoomId = null
    }
}