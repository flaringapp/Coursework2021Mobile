package com.flaringapp.coursework2021.presentation.features.resident.list.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.flaringapp.coursework2021.databinding.ViewHolderResidentBinding
import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItemViewHolder
import com.flaringapp.coursework2021.presentation.features.resident.list.models.ResidentViewData
import com.flaringapp.coursework2021.presentation.utils.inflater
import com.flaringapp.coursework2021.presentation.utils.textWithVisibility

class ResidentViewHolder(
    private val binding: ViewHolderResidentBinding
): MutableListItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = ResidentViewHolder(
            ViewHolderResidentBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(
        item: ResidentViewData,
        onOptionsClicked: (String) -> Unit
    ) = with(binding) {
        textName.text = item.name
        textDescription.textWithVisibility = item.description
        textEmail.textWithVisibility = item.email
        textCoworking.textWithVisibility = item.coworking

        buttonOptions.setOnClickListener { onOptionsClicked(item.id) }
    }

    override fun setIsEditable(isEditable: Boolean) {
        binding.buttonOptions.isVisible = isEditable
    }
}