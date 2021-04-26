package com.flaringapp.coursework2021.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.clearGlide() {
    Glide.with(this)
        .clear(this)
}