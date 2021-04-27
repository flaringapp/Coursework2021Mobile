package com.flaringapp.coursework2021.presentation.common.textresolver

import android.os.Parcelable
import com.flaringapp.coursework2021.data.text.TextProvider

interface TextResolver: Parcelable {

    fun resolveText(textProvider: TextProvider): CharSequence

}