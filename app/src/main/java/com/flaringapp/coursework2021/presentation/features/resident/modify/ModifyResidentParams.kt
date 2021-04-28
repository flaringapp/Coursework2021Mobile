package com.flaringapp.coursework2021.presentation.features.resident.modify

import android.os.Parcelable
import com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour.ModifyManagerBehaviour
import com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour.ModifyResidentBehaviour
import kotlinx.parcelize.Parcelize

@Parcelize
class ModifyResidentParams(
    val buildingId: String?,
    val behaviour: ModifyResidentBehaviour
): Parcelable