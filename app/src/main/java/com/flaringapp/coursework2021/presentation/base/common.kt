package com.flaringapp.coursework2021.presentation.base

import android.app.AlertDialog
import android.content.Context


fun Context.showMessageDialog(message: String) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(
            "OK"
        ) { _, _ -> }
        .show()
}