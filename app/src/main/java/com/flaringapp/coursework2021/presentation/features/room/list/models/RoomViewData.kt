package com.flaringapp.coursework2021.presentation.features.room.list.models

import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItem

class RoomViewData(
    override val id: String,
    val name: CharSequence,
    val description: CharSequence?,
    val hasBoard: Boolean?,
    val hasBalcony: Boolean?,
    val workplacesCount: CharSequence,
    val windowsCount: CharSequence?,
    val price: CharSequence?,
    val area: CharSequence?,
    val roomType: CharSequence?,
): MutableListItem