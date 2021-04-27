package com.flaringapp.coursework2021.presentation.features.building.modify.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.building.modify.behaviour.ModifyBuildingBehaviour

abstract class ModifyBuildingModel: BaseViewModel() {

    abstract val nameData: LiveData<CharSequence>
    abstract val descriptionData: LiveData<CharSequence>
    abstract val latitudeData: LiveData<CharSequence>
    abstract val longitudeData: LiveData<CharSequence>
    abstract val addressData: LiveData<CharSequence>
    abstract val areaData: LiveData<CharSequence>

    abstract val nameErrorData: LiveData<Int?>
    abstract val latitudeErrorData: LiveData<Int?>
    abstract val longitudeErrorData: LiveData<Int?>
    abstract val areaErrorData: LiveData<Int?>

    abstract val loadingData: LiveData<Boolean>

    abstract val closeScreenData: LiveData<Unit>

    abstract fun initBehaviour(behaviour: ModifyBuildingBehaviour)

    abstract fun handleNameChanged(name: String)
    abstract fun handleDescriptionChanged(description: String)
    abstract fun handleLatitudeChanged(latitude: String)
    abstract fun handleLongitudeChanged(longitude: String)
    abstract fun handleAddressChanged(address: String)
    abstract fun handleAreaChanged(area: String)

    abstract fun saveBuilding()

}