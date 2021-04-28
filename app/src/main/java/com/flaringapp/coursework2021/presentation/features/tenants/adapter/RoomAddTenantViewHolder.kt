package com.flaringapp.coursework2021.presentation.features.tenants.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.databinding.ViewHolderAddTenantBinding
import com.flaringapp.coursework2021.presentation.utils.inflater

class RoomAddTenantViewHolder(
    private val binding: ViewHolderAddTenantBinding
): RoomTenantsItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = RoomAddTenantViewHolder(
            ViewHolderAddTenantBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(onClicked: () -> Unit) {
        binding.root.setOnClickListener { onClicked() }
    }

    override fun release() {
        binding.root.setOnClickListener(null)
    }

}