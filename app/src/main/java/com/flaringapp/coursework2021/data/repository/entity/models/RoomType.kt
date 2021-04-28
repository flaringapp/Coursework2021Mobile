package com.flaringapp.coursework2021.data.repository.entity.models

import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.text.TextProvider

sealed class RoomType {

    abstract fun formatName(textProvider: TextProvider): CharSequence

    object OpenSpace: RoomType() {
        override fun formatName(textProvider: TextProvider): CharSequence {
            return textProvider.getText(R.string.room_type_open_space)
        }
    }

    object Private: RoomType() {
        override fun formatName(textProvider: TextProvider): CharSequence {
            return textProvider.getText(R.string.room_type_private)
        }
    }
}