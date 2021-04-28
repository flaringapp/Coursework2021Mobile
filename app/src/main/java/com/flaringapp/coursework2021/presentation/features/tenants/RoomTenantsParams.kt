package com.flaringapp.coursework2021.presentation.features.tenants

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RoomTenantsParams(
    val roomId: String,
    val roomName: String,
    val maxTenantsCount: Int
): Parcelable