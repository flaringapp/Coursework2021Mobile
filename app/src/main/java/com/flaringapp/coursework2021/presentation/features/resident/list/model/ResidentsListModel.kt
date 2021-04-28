package com.flaringapp.coursework2021.presentation.features.resident.list.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.resident.list.models.ResidentViewData

abstract class ResidentsListModel: BaseViewModel() {

    abstract val residentsData: LiveData<List<ResidentViewData>>
    abstract val addResidentData: LiveData<ResidentViewData>
    abstract val updateResidentData: LiveData<ResidentViewData>
    abstract val deleteResidentData: LiveData<String>

    abstract val openResidentActionsData: LiveData<Unit>

    abstract val openConfirmDeleteResidentData: LiveData<String>

    abstract val openCreateResidentData: LiveData<String?>
    abstract val openEditResidentData: LiveData<Resident>

    abstract fun createNewResident()

    abstract fun handleResidentOptions(id: String)

    abstract fun editSelectedResident()

    abstract fun deleteSelectedResident()
    abstract fun confirmDeleteSelectedResident()

}