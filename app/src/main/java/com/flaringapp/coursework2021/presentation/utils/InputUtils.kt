package com.flaringapp.coursework2021.presentation.utils

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.flaringapp.coursework2021.app.common.Action

fun EditText.doOnDoneClicked(action: Action) {
    doOnImeAction(EditorInfo.IME_ACTION_DONE, action)
}

fun EditText.doOnImeAction(id: Int, action: Action) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == id) {
            action()
            true
        } else false
    }
}