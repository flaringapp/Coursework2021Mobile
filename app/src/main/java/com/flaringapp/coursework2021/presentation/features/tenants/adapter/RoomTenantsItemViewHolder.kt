package com.flaringapp.coursework2021.presentation.features.tenants.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RoomTenantsItemViewHolder protected constructor(view: View) :
    RecyclerView.ViewHolder(view) {

    open fun release() {}

}