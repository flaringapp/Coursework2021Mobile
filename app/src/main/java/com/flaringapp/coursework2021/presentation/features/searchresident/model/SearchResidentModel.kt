package com.flaringapp.coursework2021.presentation.features.searchresident.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.searchresident.models.SearchResidentViewData

abstract class SearchResidentModel: BaseViewModel() {

    abstract val residentsData: LiveData<List<SearchResidentViewData>>

    abstract val searchResultData: LiveData<Resident>

    abstract fun handleResidentSelected(id: String)

}