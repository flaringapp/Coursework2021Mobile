package com.flaringapp.coursework2021.presentation.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

var TextView.textWithVisibility: CharSequence?
    get() = text
    set(value) {
        isVisible = value?.isNotEmpty() == true
        text = value
    }

fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
    start: Drawable? = null,
    top: Drawable? = null,
    end: Drawable? = null,
    bottom: Drawable? = null
) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        start ?: this.compoundDrawablesRelative[0],
        top ?: this.compoundDrawablesRelative[1],
        end ?: this.compoundDrawablesRelative[2],
        bottom ?: this.compoundDrawablesRelative[3],
    )
}

fun RecyclerView.postScrollToBottom() {
    post {
        smoothScrollToPosition(adapter?.itemCount ?: return@post)
    }
}


fun Context.dpi(dp: Float): Int {
    return resources.dp(dp).toInt()
}

fun Context.dpi(dp: Int): Int {
    return resources.dp(dp).toInt()
}

fun Context.dp(dp: Float): Float {
    return resources.dp(dp)
}

fun Context.dp(dp: Int): Float {
    return resources.dp(dp)
}

fun Context.spi(sp: Float): Int {
    return resources.sp(sp).toInt()
}

fun Context.spi(sp: Int): Int {
    return resources.sp(sp).toInt()
}

fun Context.sp(sp: Float): Float {
    return resources.sp(sp)
}

fun Context.sp(sp: Int): Float {
    return resources.sp(sp)
}

fun Resources.dp(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

fun Resources.dp(dp: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics)
}

fun Resources.sp(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, displayMetrics)
}

fun Resources.sp(sp: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), displayMetrics)
}