package com.flaringapp.coursework2021.data.repository.entity.models

import android.os.Parcelable
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize

sealed class RoomType: Parcelable {

    abstract fun formatName(textProvider: TextProvider): CharSequence

    @Parcelize
    object OpenSpace: RoomType() {
        override fun formatName(textProvider: TextProvider): CharSequence {
            return textProvider.getText(R.string.room_type_open_space)
        }
    }

    @Parcelize
    object Private: RoomType() {
        override fun formatName(textProvider: TextProvider): CharSequence {
            return textProvider.getText(R.string.room_type_private)
        }
    }
}