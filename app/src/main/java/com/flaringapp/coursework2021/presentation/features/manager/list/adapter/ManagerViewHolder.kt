package com.flaringapp.coursework2021.presentation.features.manager.list.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.flaringapp.coursework2021.databinding.ViewHolderManagerBinding
import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItemViewHolder
import com.flaringapp.coursework2021.presentation.features.manager.list.models.ManagerViewData
import com.flaringapp.coursework2021.presentation.utils.inflater
import com.flaringapp.coursework2021.presentation.utils.textWithVisibility

class ManagerViewHolder(
    private val binding: ViewHolderManagerBinding
): MutableListItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = ManagerViewHolder(
            ViewHolderManagerBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(
        item: ManagerViewData,
        onOptionsClicked: (String) -> Unit
    ) = with(binding) {
        textName.text = item.name
        textDescription.textWithVisibility = item.description
        textEmail.text = item.email
        textCoworking.text = item.coworking

        buttonOptions.setOnClickListener { onOptionsClicked(item.id) }
    }

    override fun setIsEditable(isEditable: Boolean) {
        binding.buttonOptions.isVisible = isEditable
    }
}