package com.flaringapp.coursework2021.presentation.features.building.list.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.app.common.Action
import com.flaringapp.coursework2021.databinding.ViewHolderBuildingBinding
import com.flaringapp.coursework2021.presentation.features.building.list.models.BuildingViewData
import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListAddItemViewHolder
import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItemViewHolder
import com.flaringapp.coursework2021.presentation.utils.inflater

class AddBuildingViewHolder(
    private val binding: ViewHolderBuildingBinding
): MutableListAddItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AddBuildingViewHolder(
            ViewHolderBuildingBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: BuildingViewData, editBuilding: Action) = with(binding) {
        textName.text = item.name
        textDescription.text = item.description
        textLocation.text = item.location
        textAddress.text = item.address
        textArea.text = item.area

        root.setOnClickListener { editBuilding() }
    }

}