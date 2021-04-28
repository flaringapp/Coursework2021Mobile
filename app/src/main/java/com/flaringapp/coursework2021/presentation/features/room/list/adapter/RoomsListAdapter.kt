package com.flaringapp.coursework2021.presentation.features.room.list.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.Action
import com.flaringapp.coursework2021.presentation.features.common.adapter.SimpleMutableListAdapter
import com.flaringapp.coursework2021.presentation.features.room.list.models.RoomViewData

class RoomsListAdapter(
    addNewRoom: Action,
    private val onRoomOptions: (String) -> Unit,
): SimpleMutableListAdapter<RoomViewHolder, RoomViewData>(
    R.string.button_add_room,
    addNewRoom
) {

    override fun createItemViewHolder(parent: ViewGroup): RoomViewHolder {
        return RoomViewHolder.create(parent)
    }

    override fun bindItemViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(items[position], onRoomOptions)
    }
}