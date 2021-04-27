package com.flaringapp.coursework2021.data.repository.entity.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Building(
    val id: String,
    val name: String,
    val description: String,
    val location: GeoLocation? = null,
    val address: String? = null,
    val area: Float? = null,
): Parcelable {

    companion object {
        const val NO_ID = "0"
    }

}