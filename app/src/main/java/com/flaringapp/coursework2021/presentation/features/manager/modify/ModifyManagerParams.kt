package com.flaringapp.coursework2021.presentation.features.manager.modify

import android.os.Parcelable
import com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour.ModifyManagerBehaviour
import kotlinx.parcelize.Parcelize

@Parcelize
class ModifyManagerParams(
    val behaviour: ModifyManagerBehaviour
): Parcelable