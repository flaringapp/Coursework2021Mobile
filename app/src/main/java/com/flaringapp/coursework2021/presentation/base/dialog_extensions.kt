package com.flaringapp.coursework2021.presentation.base

import android.view.View
import androidx.fragment.app.DialogFragment
import com.flaringapp.coursework2021.R

fun DialogFragment.setupRoundedBackground(root: View) {
    root.setBackgroundResource(R.drawable.bg_dialog)
    root.clipToOutline = true
}