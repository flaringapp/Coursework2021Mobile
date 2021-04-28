package com.flaringapp.coursework2021.presentation.features.manager.list.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.manager.list.models.ManagerViewData

abstract class ManagersListModel: BaseViewModel() {

    abstract val managersData: LiveData<List<ManagerViewData>>
    abstract val addManagerData: LiveData<ManagerViewData>
    abstract val updateManagerData: LiveData<ManagerViewData>
    abstract val deleteManagerData: LiveData<String>

    abstract val openManagerActionsData: LiveData<Unit>

    abstract val openConfirmDeleteManagerData: LiveData<String>

    abstract val openCreateManagerData: LiveData<Unit>
    abstract val openEditManagerData: LiveData<Manager>

    abstract fun createNewManager()

    abstract fun handleManagerOptions(id: String)

    abstract fun editSelectedManager()

    abstract fun deleteSelectedManager()
    abstract fun confirmDeleteSelectedManager()

}