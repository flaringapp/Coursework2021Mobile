package com.flaringapp.coursework2021.presentation.features.building.list.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.Action
import com.flaringapp.coursework2021.presentation.features.building.list.models.BuildingViewData
import com.flaringapp.coursework2021.presentation.features.common.adapter.SimpleMutableListAdapter

class BuildingsListAdapter(
    private val openBuilding: (String) -> Unit,
    private val onBuildingOptions: (String) -> Unit,
    addNewBuilding: Action,
): SimpleMutableListAdapter<BuildingViewHolder, BuildingViewData>(
    R.string.button_add_building,
    addNewBuilding
) {

    override fun createItemViewHolder(parent: ViewGroup): BuildingViewHolder {
        return BuildingViewHolder.create(parent)
    }

    override fun bindItemViewHolder(holder: BuildingViewHolder, position: Int) {
        holder.bind(items[position], openBuilding, onBuildingOptions)
    }
}