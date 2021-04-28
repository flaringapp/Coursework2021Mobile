package com.flaringapp.coursework2021.presentation.features.room.modify.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.data.repository.entity.models.RoomType
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.room.modify.behaviour.ModifyRoomBehaviour

abstract class ModifyRoomModel: BaseViewModel() {

    abstract val nameData: LiveData<CharSequence>
    abstract val descriptionData: LiveData<CharSequence>
    abstract val roomTypeData: LiveData<RoomType>
    abstract val priceData: LiveData<String>
    abstract val hasBoardData: LiveData<Boolean>
    abstract val hasBalconyData: LiveData<Boolean>
    abstract val workplacesCountData: LiveData<Int>
    abstract val windowCountData: LiveData<Int>
    abstract val areaData: LiveData<CharSequence>

    abstract val nameErrorData: LiveData<Int?>
    abstract val priceErrorData: LiveData<Int?>
    abstract val areaErrorData: LiveData<Int?>

    abstract val loadingData: LiveData<Boolean>

    abstract val closeScreenData: LiveData<Unit>

    abstract fun init(buildingId: String, behaviour: ModifyRoomBehaviour)

    abstract fun handleNameChanged(name: String)
    abstract fun handleDescriptionChanged(description: String)
    abstract fun handleRoomTypeSelected(roomType: RoomType)
    abstract fun handlePriceChanged(price: String)
    abstract fun handleHasBoardChanged(hasBoard: Boolean)
    abstract fun handleHasBalconyChanged(hasBalcony: Boolean)
    abstract fun handleWorkplacesCountChanged(workplacesCount: Int)
    abstract fun handleWindowCountChanged(windowCount: Int)
    abstract fun handleAreaChanged(area: String)

    abstract fun saveRoom()

}