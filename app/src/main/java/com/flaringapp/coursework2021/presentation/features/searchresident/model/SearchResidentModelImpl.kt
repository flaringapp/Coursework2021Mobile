package com.flaringapp.coursework2021.presentation.features.searchresident.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.residents.ResidentsRepository
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.searchresident.models.SearchResidentViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent

class SearchResidentModelImpl(
    private val repository: ResidentsRepository,
    private val textProvider: TextProvider
) : SearchResidentModel() {

    companion object {
        // TODO important read building id
        private const val BUILDING_ID = "1"
    }

    override val residentsData = MutableLiveData<List<SearchResidentViewData>>()

    override val searchResultData = SingleLiveEvent<Resident>()

    private var residents: List<Resident> = emptyList()

    init {
        loadResidents()
    }

    override fun handleResidentSelected(id: String) {
        val resident = residents.find { it.id == id } ?: return
        searchResultData.value = resident
    }

    private fun loadResidents() {
        viewModelScope.launchOnIO {
            val loadedResidents = safeCall {
                repository.getResidents(BUILDING_ID)
            } ?: return@launchOnIO

            val viewData = loadedResidents.map { it.toViewData() }

            withMainContext {
                residents = loadedResidents
                residentsData.value = viewData
            }
        }
    }

    private fun Resident.toViewData() = SearchResidentViewData(
        id,
        formatNameSurname(textProvider).toString()
    )
}