package com.flaringapp.coursework2021.presentation.features.building.list.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.flaringapp.coursework2021.databinding.ViewHolderBuildingBinding
import com.flaringapp.coursework2021.presentation.features.building.list.models.BuildingViewData
import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItemViewHolder
import com.flaringapp.coursework2021.presentation.utils.inflater
import com.flaringapp.coursework2021.presentation.utils.textWithVisibility

class BuildingViewHolder(
    private val binding: ViewHolderBuildingBinding
): MutableListItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = BuildingViewHolder(
            ViewHolderBuildingBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: BuildingViewData, onOptionsClicked: (String) -> Unit) = with(binding) {
        textName.text = item.name
        textDescription.textWithVisibility = item.description
        textLocation.textWithVisibility = item.location
        textAddress.textWithVisibility = item.address
        textArea.textWithVisibility = item.area

        buttonOptions.setOnClickListener { onOptionsClicked(item.id) }
    }

    override fun setIsEditable(isEditable: Boolean) {
        binding.imageItem.isVisible = isEditable
    }
}