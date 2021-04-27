package com.flaringapp.coursework2021.presentation.features.building.modify

import android.os.Parcelable
import com.flaringapp.coursework2021.presentation.features.building.modify.behaviour.ModifyBuildingBehaviour
import kotlinx.parcelize.Parcelize

@Parcelize
class ModifyBuildingParams(
    val behaviour: ModifyBuildingBehaviour
): Parcelable