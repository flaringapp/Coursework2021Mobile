package com.flaringapp.coursework2021.presentation.features.tenants.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.databinding.ViewHolderTenantBinding
import com.flaringapp.coursework2021.presentation.features.tenants.models.TenantViewData
import com.flaringapp.coursework2021.presentation.utils.inflater

class RoomTenantViewHolder(
    private val binding: ViewHolderTenantBinding
): RoomTenantsItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = RoomTenantViewHolder(
            ViewHolderTenantBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: TenantViewData, onRemove: (String) -> Unit) = with(binding) {
        textTenantName.text = item.tenantName

        buttonDelete.setOnClickListener { onRemove(item.rentId) }
    }

    override fun release() {
        binding.buttonDelete.setOnClickListener(null)
    }

}