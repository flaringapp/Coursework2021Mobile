package com.flaringapp.coursework2021.presentation.features.managers.list.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.app.common.*
import com.flaringapp.coursework2021.data.repository.manager.ManagersRepository
import com.flaringapp.coursework2021.data.repository.manager.models.Manager
import com.flaringapp.coursework2021.data.repository.manager.storage.ManagersStorage
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.managers.list.models.ManagerViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.valueIfHasObservers

class ManagersListModelImpl(
    private val managersRepository: ManagersRepository,
    managersStorage: ManagersStorage,
    private val textProvider: TextProvider,
) : ManagersListModel() {

    override val managersData = MutableLiveData<List<ManagerViewData>>()
    override val addManagerData = SingleLiveEvent<ManagerViewData>()
    override val updateManagerData = SingleLiveEvent<ManagerViewData>()
    override val deleteManagerData = SingleLiveEvent<String>()

    override val openManagerActionsData = SingleLiveEvent<Unit>()

    override val openConfirmDeleteManagerData = SingleLiveEvent<String>()

    override val openCreateManagerData = SingleLiveEvent<Unit>()
    override val openEditManagerData = SingleLiveEvent<Manager>()

    private val managers: MutableList<Manager> = mutableListOf()

    private var pendingOptionsManagerId: String? = null

    init {
        loadManagers()

        // TODO refactor general list validation
        managersStorage.addManagerFlow.collectOn(viewModelScope) { addedManager ->
            managers.add(addedManager)
            addManagerData.valueIfHasObservers = addedManager.toViewData()

            managersData.value = managers.map { it.toViewData() }
        }
        managersStorage.editManagerFlow.collectOn(viewModelScope) { changedManager ->
            managers.replaceFirst(changedManager) { it.id == changedManager.id }
            updateManagerData.valueIfHasObservers = changedManager.toViewData()

            managersData.value = managers.map { it.toViewData() }
        }
    }

    override fun createNewManager() {
        openCreateManagerData.value = Unit
    }

    override fun handleManagerOptions(id: String) {
        pendingOptionsManagerId = id
        openManagerActionsData.value = Unit
    }

    override fun editSelectedManager() {
        pendingManagerAction(this::editManager)
    }

    override fun deleteSelectedManager() {
        val managerId = pendingOptionsManagerId ?: return
        val manager = managers.find { it.id == managerId } ?: return
        openConfirmDeleteManagerData.value = manager.name
    }

    override fun confirmDeleteSelectedManager() {
        pendingManagerAction(this::deleteManager)
    }

    private fun loadManagers() {
        viewModelScope.launchOnIO {
            val loadedManagers = safeCall { managersRepository.getManagers() } ?: return@launchOnIO

            val managersViewData = loadedManagers.map { it.toViewData() }

            withMainContext {
                managers.clearAndAdd(loadedManagers)
                managersData.value = managersViewData
            }
        }
    }

    private fun editManager(id: String) {
        val manager = managers.find { it.id == id } ?: return
        openEditManagerData.value = manager
    }

    private fun deleteManager(id: String) {
        viewModelScope.launchOnIO {
            safeCall { managersRepository.deleteManager(id) } ?: return@launchOnIO

            withMainContext {
                deleteManagerData.value = id
            }
        }
    }

    private fun Manager.toViewData() = ManagerViewData(
        id,
        textProvider.formatNameSurname(name, surname),
        description,
        email,
        buildingName,
    )

    private fun pendingManagerAction(action: (String) -> Unit) {
        val id = pendingOptionsManagerId ?: return
        action(id)
        pendingOptionsManagerId = null
    }
}