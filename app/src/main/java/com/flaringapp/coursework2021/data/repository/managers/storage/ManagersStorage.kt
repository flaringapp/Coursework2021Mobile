package com.flaringapp.coursework2021.data.repository.managers.storage

import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import kotlinx.coroutines.flow.Flow

interface ManagersStorage {

    val addManagerFlow: Flow<Manager>
    val editManagerFlow: Flow<Manager>
    val deleteManagerFlow: Flow<String>

}