package com.flaringapp.coursework2021.data.repository.residents.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Resident(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val description: String,
    val buildingId: String?,
    val buildingName: String?,
): Parcelable {
    companion object {
        const val NO_ID = "0"
    }
}