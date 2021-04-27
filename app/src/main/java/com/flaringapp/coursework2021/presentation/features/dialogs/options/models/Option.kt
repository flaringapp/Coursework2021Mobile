package com.flaringapp.coursework2021.presentation.features.dialogs.options.models

import android.os.Parcelable
import com.flaringapp.coursework2021.data.text.TextProvider

interface Option : Parcelable {
    val key: String

    fun resolveName(textProvider: TextProvider): CharSequence
}