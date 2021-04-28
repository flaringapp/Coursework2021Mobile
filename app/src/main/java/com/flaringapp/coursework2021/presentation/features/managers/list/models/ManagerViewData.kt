package com.flaringapp.coursework2021.presentation.features.managers.list.models

import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItem

class ManagerViewData(
    override val id: String,
    val name: CharSequence,
    val description: CharSequence?,
    val email: CharSequence?,
    val coworking: CharSequence?,
): MutableListItem