package com.flaringapp.coursework2021.presentation.features.resident.list.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.Action
import com.flaringapp.coursework2021.presentation.features.common.adapter.SimpleMutableListAdapter
import com.flaringapp.coursework2021.presentation.features.resident.list.models.ResidentViewData

class ResidentsListAdapter(
    private val onResidentOptions: (String) -> Unit,
    addNewResident: Action,
) : SimpleMutableListAdapter<ResidentViewHolder, ResidentViewData>(
    R.string.button_add_resident,
    addNewResident
) {

    override fun createItemViewHolder(parent: ViewGroup): ResidentViewHolder {
        return ResidentViewHolder.create(parent)
    }

    override fun bindItemViewHolder(holder: ResidentViewHolder, position: Int) {
        holder.bind(items[position], onResidentOptions)
    }
}