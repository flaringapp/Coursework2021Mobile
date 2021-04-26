package com.flaringapp.coursework2021.presentation.utils

import android.widget.TextView
import androidx.core.view.isVisible

var TextView.textWithVisibility: CharSequence?
    get() = text
    set(value) {
        isVisible = value?.isNotEmpty() == true
        text = value
    }