package com.flaringapp.coursework2021.presentation.features.room.modify.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.isCorrectFloat
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.data.repository.entity.models.RoomType
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.room.modify.behaviour.ModifyRoomBehaviour
import com.flaringapp.coursework2021.presentation.features.room.modify.models.RoomEditableData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.startLoadingTask

class ModifyRoomModelImpl(
    private val textProvider: TextProvider
) : ModifyRoomModel() {

    override val nameData = SingleLiveEvent<CharSequence>()
    override val descriptionData = SingleLiveEvent<CharSequence>()
    override val roomTypeData = MutableLiveData<RoomType>()
    override val priceData = MutableLiveData<String>()
    override val hasBoardData = SingleLiveEvent<Boolean>()
    override val hasBalconyData = SingleLiveEvent<Boolean>()
    override val workplacesCountData = SingleLiveEvent<Int>()
    override val windowCountData = SingleLiveEvent<Int>()
    override val areaData = SingleLiveEvent<CharSequence>()

    override val nameErrorData = SingleLiveEvent<Int?>()
    override val priceErrorData = SingleLiveEvent<Int?>()
    override val areaErrorData = SingleLiveEvent<Int?>()

    override val loadingData = MutableLiveData(false)

    override val closeScreenData = SingleLiveEvent<Unit>()

    private lateinit var buildingId: String

    private lateinit var behaviour: ModifyRoomBehaviour

    private var sourceRoom: Room? = null
    private lateinit var editor: RoomEditableData

    override fun init(buildingId: String, behaviour: ModifyRoomBehaviour) {
        this.buildingId = buildingId
        this.behaviour = behaviour
        sourceRoom = behaviour.room
        editor = behaviour.createPreliminaryData(textProvider) ?: RoomEditableData()
        editor.setup()
    }

    override fun handleNameChanged(name: String) {
        editor.name = name
        nameErrorData.value = null
    }

    override fun handleDescriptionChanged(description: String) {
        editor.description = description
    }

    override fun handleRoomTypeSelected(roomType: RoomType) {
        editor.type = roomType
        roomTypeData.value = roomType
    }

    override fun handlePriceChanged(price: String) {
        editor.price = price
        priceErrorData.value = null
    }

    override fun handleHasBoardChanged(hasBoard: Boolean) {
        editor.hasBoard = hasBoard
    }

    override fun handleHasBalconyChanged(hasBalcony: Boolean) {
        editor.hasBalcony = hasBalcony
    }

    override fun handleWorkplacesCountChanged(workplacesCount: Int) {
        editor.workplacesCount = workplacesCount
    }

    override fun handleWindowCountChanged(windowCount: Int) {
        editor.windowCount = windowCount
    }

    override fun handleAreaChanged(area: String) {
        editor.area = area
        areaErrorData.value = null
    }

    override fun saveRoom() {
        if (!isValid()) return
        performSaveRoom()
    }

    private fun RoomEditableData.setup() {
        nameData.value = name
        descriptionData.value = description
        roomTypeData.value = type
        priceData.value = price
        hasBoardData.value = hasBoard
        hasBalconyData.value = hasBalcony
        workplacesCountData.value = workplacesCount
        windowCountData.value = windowCount
        areaData.value = area
    }

    private fun performSaveRoom() {
        viewModelScope.startLoadingTask(loadingData) {
            val room = editor.toRoom(sourceRoom, buildingId)
            safeCall { behaviour.modifyRoom(room) }
                ?: return@startLoadingTask

            withMainContext {
                closeScreenData.value = Unit
            }
        }
    }

    private fun isValid(): Boolean {
        when {
            editor.name.trim().isEmpty() -> {
                nameErrorData.value = R.string.error_room_empty_name
            }
            editor.price.trim().isEmpty() -> {
                priceErrorData.value = R.string.error_room_empty_price
            }
            !editor.price.isCorrectFloat() -> {
                priceErrorData.value = R.string.error_room_invalid_price
            }
            !editor.area.trim().isCorrectFloat() -> {
                areaErrorData.value = R.string.error_room_invalid_area
            }
            else -> return true
        }
        return false
    }
}