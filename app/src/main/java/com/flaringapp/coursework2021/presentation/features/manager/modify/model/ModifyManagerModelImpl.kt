package com.flaringapp.coursework2021.presentation.features.manager.modify.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.entity.EntityRepository
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour.ModifyManagerBehaviour
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerBuildingViewData
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerEditableData
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerSelectBuildingsViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.startLoadingTask

class ModifyManagerModelImpl(
    private val entityRepository: EntityRepository
) : ModifyManagerModel() {

    override val nameData = SingleLiveEvent<CharSequence>()
    override val surnameData = SingleLiveEvent<CharSequence>()
    override val emailData = SingleLiveEvent<CharSequence>()
    override val descriptionData = SingleLiveEvent<CharSequence>()
    override val buildingsData = SingleLiveEvent<ManagerSelectBuildingsViewData>()

    override val nameErrorData = SingleLiveEvent<Int?>()
    override val surnameErrorData = SingleLiveEvent<Int?>()
    override val emailErrorData = SingleLiveEvent<Int?>()
    override val buildingErrorData = SingleLiveEvent<Int?>()

    override val loadingData = MutableLiveData(false)

    override val closeScreenData = SingleLiveEvent<Unit>()

    private lateinit var behaviour: ModifyManagerBehaviour

    private var sourceManager: Manager? = null
    private lateinit var editor: ManagerEditableData

    private var buildings: List<Building> = emptyList()

    override fun initBehaviour(behaviour: ModifyManagerBehaviour) {
        this.behaviour = behaviour
        sourceManager = behaviour.manager
        editor = behaviour.preliminaryData ?: ManagerEditableData()
        editor.setup()
    }

    override fun loadData() {
        loadBuildings()
    }

    override fun handleNameChanged(name: String) {
        editor.name = name
        nameErrorData.value = null
    }

    override fun handleSurnameChanged(surname: String) {
        editor.surname = surname
        surnameErrorData.value = null
    }

    override fun handleEmailChanged(email: String) {
        editor.email = email
        emailErrorData.value = null
    }

    override fun handleDescriptionChanged(description: String) {
        editor.description = description
    }

    override fun handleBuildingChanged(id: String) {
        val building = buildings.find { it.id == id } ?: return
        editor.coworkingId = id
        editor.coworkingName = building.name

        buildingErrorData.value = null
    }

    override fun saveManager() {
        if (!isValid()) return
        performSaveManager()
    }

    private fun ManagerEditableData.setup() {
        nameData.value = name
        surnameData.value = surname
        emailData.value = email
        descriptionData.value = description
    }

    private fun loadBuildings() {
        viewModelScope.startLoadingTask(loadingData) {
            val loadedBuildings = safeCall {
                entityRepository.getBuildings()
            } ?: return@startLoadingTask

            val viewBuildings = loadedBuildings.map { it.toViewData() }

            val buildingsViewData = ManagerSelectBuildingsViewData(
                viewBuildings,
                editor.coworkingName ?: viewBuildings.firstOrNull()?.name
            )

            withMainContext {
                buildings = loadedBuildings
                buildingsData.value = buildingsViewData
            }
        }
    }

    private fun performSaveManager() {
        viewModelScope.startLoadingTask(loadingData) {
            val manager = editor.toManager(sourceManager)
            safeCall { behaviour.modifyManager(manager) }
                ?: return@startLoadingTask

            withMainContext {
                closeScreenData.value = Unit
            }
        }
    }

    private fun isValid(): Boolean {
        when {
            editor.name.trim().isEmpty() -> {
                nameErrorData.value = R.string.error_manager_empty_name
            }
            editor.surname.trim().isEmpty() -> {
                surnameErrorData.value = R.string.error_manager_empty_surname
            }
            editor.email.trim().isEmpty() -> {
                emailErrorData.value = R.string.error_manager_empty_email
            }
            editor.coworkingId == null || editor.coworkingName == null -> {
                buildingErrorData.value = R.string.error_manager_empty_building
            }
            else -> return true
        }
        return false
    }

    private fun Building.toViewData() = ManagerBuildingViewData(id, name)
}