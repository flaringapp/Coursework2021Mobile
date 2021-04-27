package com.flaringapp.coursework2021.presentation.features.building.list.models

import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItem

class BuildingViewData(
    override val id: String,
    val name: CharSequence,
    val description: CharSequence?,
    val location: CharSequence?,
    val address: CharSequence?,
    val area: CharSequence?
): MutableListItem