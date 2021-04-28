package com.flaringapp.coursework2021.data.repository.residents.storage

import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import kotlinx.coroutines.flow.Flow

interface ResidentsStorage {

    val addResidentFlow: Flow<Resident>
    val editResidentFlow: Flow<Resident>
    val deleteResidentFlow: Flow<String>

}