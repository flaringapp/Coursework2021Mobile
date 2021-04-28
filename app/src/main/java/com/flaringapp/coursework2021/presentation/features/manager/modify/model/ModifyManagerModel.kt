package com.flaringapp.coursework2021.presentation.features.manager.modify.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour.ModifyManagerBehaviour
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerBuildingViewData
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerSelectBuildingsViewData

abstract class ModifyManagerModel: BaseViewModel() {

    abstract val nameData: LiveData<CharSequence>
    abstract val surnameData: LiveData<CharSequence>
    abstract val emailData: LiveData<CharSequence>
    abstract val descriptionData: LiveData<CharSequence>

    abstract val buildingsData: LiveData<ManagerSelectBuildingsViewData>

    abstract val nameErrorData: LiveData<Int?>
    abstract val surnameErrorData: LiveData<Int?>
    abstract val emailErrorData: LiveData<Int?>
    abstract val buildingErrorData: LiveData<Int?>

    abstract val loadingData: LiveData<Boolean>

    abstract val closeScreenData: LiveData<Unit>

    abstract fun initBehaviour(behaviour: ModifyManagerBehaviour)

    abstract fun loadData()

    abstract fun handleNameChanged(name: String)
    abstract fun handleSurnameChanged(surname: String)
    abstract fun handleEmailChanged(email: String)
    abstract fun handleDescriptionChanged(description: String)
    abstract fun handleBuildingChanged(id: String)

    abstract fun saveManager()

}