package com.flaringapp.coursework2021.presentation.features.manager.list.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.Action
import com.flaringapp.coursework2021.presentation.features.common.adapter.SimpleMutableListAdapter
import com.flaringapp.coursework2021.presentation.features.manager.list.models.ManagerViewData

class ManagerListAdapter(
    private val onManagerOptions: (String) -> Unit,
    addNewManager: Action,
): SimpleMutableListAdapter<ManagerViewHolder, ManagerViewData>(
    R.string.button_add_manager,
    addNewManager
) {

    override fun createItemViewHolder(parent: ViewGroup): ManagerViewHolder {
        return ManagerViewHolder.create(parent)
    }

    override fun bindItemViewHolder(holder: ManagerViewHolder, position: Int) {
        holder.bind(items[position], onManagerOptions)
    }
}