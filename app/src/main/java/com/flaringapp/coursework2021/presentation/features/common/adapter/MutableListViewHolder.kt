package com.flaringapp.coursework2021.presentation.features.common.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MutableListViewHolder protected constructor(view: View) :
    RecyclerView.ViewHolder(view) {

    open fun release() {}

}