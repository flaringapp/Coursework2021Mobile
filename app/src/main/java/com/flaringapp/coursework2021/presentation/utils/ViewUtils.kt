package com.flaringapp.coursework2021.presentation.utils

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.core.view.isVisible

var TextView.textWithVisibility: CharSequence?
    get() = text
    set(value) {
        isVisible = value?.isNotEmpty() == true
        text = value
    }

fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
    left: Drawable? = null,
    top: Drawable? = null,
    right: Drawable? = null,
    bottom: Drawable? = null
) {
    setCompoundDrawablesWithIntrinsicBounds(
        left ?: this.compoundDrawables[0],
        top ?: this.compoundDrawables[1],
        right ?: this.compoundDrawables[2],
        bottom ?: this.compoundDrawables[3],
    )
}