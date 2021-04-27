package com.flaringapp.coursework2021.data.text

import androidx.annotation.StringRes
import com.flaringapp.coursework2021.data.repository.entity.models.GeoLocation

interface TextProvider {

    fun getText(@StringRes res: Int): CharSequence
    fun getString(@StringRes res: Int): String {
        return getText(res).toString()
    }

    fun getText(@StringRes res: Int, vararg params: Any): CharSequence

    fun formatLocation(location: GeoLocation): CharSequence

    fun formatArea(area: Float): CharSequence

}