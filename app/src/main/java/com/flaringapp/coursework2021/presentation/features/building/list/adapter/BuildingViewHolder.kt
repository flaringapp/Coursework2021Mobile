package com.flaringapp.coursework2021.presentation.features.building.list.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.databinding.ViewHolderBuildingBinding
import com.flaringapp.coursework2021.presentation.features.building.list.models.BuildingViewData
import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItemViewHolder
import com.flaringapp.coursework2021.presentation.utils.inflater

class BuildingViewHolder(
    private val binding: ViewHolderBuildingBinding
): MutableListItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = BuildingViewHolder(
            ViewHolderBuildingBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: BuildingViewData, editBuilding: (String) -> Unit) = with(binding) {
        textName.text = item.name
        textDescription.text = item.description
        textLocation.text = item.location
        textAddress.text = item.address
        textArea.text = item.area

        root.setOnClickListener { editBuilding(item.id) }
    }

    override fun setIsEditable(isEditable: Boolean) {
        binding.root.isClickable = isEditable
    }
}