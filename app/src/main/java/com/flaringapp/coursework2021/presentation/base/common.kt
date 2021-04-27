package com.flaringapp.coursework2021.presentation.base

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment

fun Context.showMessageDialog(message: String) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(
            "OK"
        ) { _, _ -> }
        .show()
}

inline fun <reified T> Fragment.parentAsListener(): T {
    return parentAsListenerOrNull()
        ?: throw IllegalStateException("Parent of type ${T::class} not found")
}

inline fun <reified T> Fragment.parentAsListenerOrNull(): T? {
    return parentFragment as? T
        ?: parentFragment?.parentFragment as? T
        ?: activity as? T
}