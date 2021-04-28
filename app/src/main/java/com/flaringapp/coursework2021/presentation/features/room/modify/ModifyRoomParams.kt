package com.flaringapp.coursework2021.presentation.features.room.modify

import android.os.Parcelable
import com.flaringapp.coursework2021.presentation.features.room.modify.behaviour.ModifyRoomBehaviour
import kotlinx.parcelize.Parcelize

@Parcelize
class ModifyRoomParams(
    val buildingId: String,
    val behaviour: ModifyRoomBehaviour
): Parcelable