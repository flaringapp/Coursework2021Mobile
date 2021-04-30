
package com.flaringapp.coursework2021.presentation.features.resident.list.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.app.common.*
import com.flaringapp.coursework2021.data.repository.profile.models.Profile
import com.flaringapp.coursework2021.data.repository.residents.ResidentsRepository
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.repository.residents.storage.ResidentsStorage
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.resident.list.models.ResidentViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.valueIfHasObservers

class ResidentsListModelImpl(
    private val profile: Profile,
    private val residentsRepository: ResidentsRepository,
    residentsStorage: ResidentsStorage,
    private val textProvider: TextProvider,
) : ResidentsListModel() {

    override val residentsData = MutableLiveData<List<ResidentViewData>>()
    override val addResidentData = SingleLiveEvent<ResidentViewData>()
    override val updateResidentData = SingleLiveEvent<ResidentViewData>()
    override val deleteResidentData = SingleLiveEvent<String>()

    override val openResidentActionsData = SingleLiveEvent<Unit>()

    override val openConfirmDeleteResidentData = SingleLiveEvent<String>()

    override val openCreateResidentData = SingleLiveEvent<String>()
    override val openEditResidentData = SingleLiveEvent<Resident>()

    private val residents: MutableList<Resident> = mutableListOf()

    private var pendingOptionsResidentId: String? = null

    init {
        loadResidents()

        // TODO refactor general list validation
        residentsStorage.addResidentFlow.collectOn(viewModelScope) { addedResident ->
            residents.add(addedResident)
            addResidentData.valueIfHasObservers = addedResident.toViewData()

            residentsData.value = residents.map { it.toViewData() }
        }
        residentsStorage.editResidentFlow.collectOn(viewModelScope) { changedResident ->
            residents.replaceFirst(changedResident) { it.id == changedResident.id }
            updateResidentData.valueIfHasObservers = changedResident.toViewData()

            residentsData.value = residents.map { it.toViewData() }
        }
    }

    override fun createNewResident() {
        openCreateResidentData.value = profile.id
    }

    override fun handleResidentOptions(id: String) {
        pendingOptionsResidentId = id
        openResidentActionsData.value = Unit
    }

    override fun editSelectedResident() {
        pendingResidentAction(this::editResident)
    }

    override fun deleteSelectedResident() {
        val residentId = pendingOptionsResidentId ?: return
        val resident = residents.find { it.id == residentId } ?: return
        openConfirmDeleteResidentData.value = resident.name
    }

    override fun confirmDeleteSelectedResident() {
        pendingResidentAction(this::deleteResident)
    }

    private fun loadResidents() {
        viewModelScope.launchOnIO {
            val loadedResidents = safeCall { residentsRepository.getResidents() } ?: return@launchOnIO

            val residentsViewData = loadedResidents.map { it.toViewData() }

            withMainContext {
                residents.clearAndAdd(loadedResidents)
                residentsData.value = residentsViewData
            }
        }
    }

    private fun editResident(id: String) {
        val resident = residents.find { it.id == id } ?: return
        openEditResidentData.value = resident
    }

    private fun deleteResident(id: String) {
        viewModelScope.launchOnIO {
            safeCall { residentsRepository.deleteResident(id) } ?: return@launchOnIO

            withMainContext {
                deleteResidentData.value = id
            }
        }
    }

    private fun Resident.toViewData() = ResidentViewData(
        id,
        textProvider.formatNameSurname(name, surname),
        description,
        email,
        buildingName,
    )

    private fun pendingResidentAction(action: (String) -> Unit) {
        val id = pendingOptionsResidentId ?: return
        action(id)
        pendingOptionsResidentId = null
    }
}