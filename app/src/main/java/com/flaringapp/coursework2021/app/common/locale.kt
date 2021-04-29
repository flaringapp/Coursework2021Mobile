package com.flaringapp.coursework2021.app.common

import android.content.Context
import android.os.Build
import java.util.*


fun deviceLanguage(): String? {
    return Locale.getDefault().language
}

fun runtimeLocale(context: Context): Locale? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val localeList = context.resources.configuration.locales
        if (localeList.isEmpty) Locale.getDefault() else localeList[0]
    } else {
        context.resources.configuration.locale
    }
}