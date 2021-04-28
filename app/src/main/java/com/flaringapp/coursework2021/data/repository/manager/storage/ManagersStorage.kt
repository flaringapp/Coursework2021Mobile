package com.flaringapp.coursework2021.data.repository.manager.storage

import com.flaringapp.coursework2021.data.repository.manager.models.Manager
import kotlinx.coroutines.flow.Flow

interface ManagersStorage {

    val addManagerFlow: Flow<Manager>
    val editManagerFlow: Flow<Manager>
    val deleteManagerFlow: Flow<String>

}