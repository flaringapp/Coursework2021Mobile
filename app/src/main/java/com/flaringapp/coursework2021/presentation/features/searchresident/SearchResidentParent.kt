package com.flaringapp.coursework2021.presentation.features.searchresident

import com.flaringapp.coursework2021.data.repository.residents.models.Resident

interface SearchResidentParent {

    fun onSearchResidentSelected(resident: Resident)

}