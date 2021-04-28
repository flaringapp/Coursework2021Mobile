package com.flaringapp.coursework2021.data.text

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import com.flaringapp.coursework2021.data.repository.entity.models.GeoLocation

interface TextProvider {

    fun getText(@StringRes res: Int): CharSequence
    fun getString(@StringRes res: Int): String {
        return getText(res).toString()
    }

    fun getText(@StringRes res: Int, vararg params: Any): CharSequence

    fun getPluralText(@PluralsRes res: Int, count: Int): String

    fun formatLocation(location: GeoLocation): CharSequence

    fun formatArea(area: Float): CharSequence

    fun formatNameSurname(name: String, surname: String): CharSequence

    fun formatPrice(price: Int): CharSequence
    fun formatPriceWithPrefix(price: Int): CharSequence

}