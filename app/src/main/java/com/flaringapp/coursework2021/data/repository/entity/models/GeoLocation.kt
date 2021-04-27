package com.flaringapp.coursework2021.data.repository.entity.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GeoLocation(
    val latitude: Double,
    val longitude: Double
): Parcelable