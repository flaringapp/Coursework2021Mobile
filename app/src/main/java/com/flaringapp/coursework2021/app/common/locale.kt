package com.flaringapp.coursework2021.app.common

import android.content.Context
import java.util.*

fun deviceLanguage(): String? {
    return Locale.getDefault().language
}

fun runtimeLocale(context: Context): Locale? {
    val localeList = context.resources.configuration.locales
    return if (localeList.isEmpty) Locale.getDefault() else localeList[0]
}