package com.flaringapp.coursework2021.presentation.features.common.adapter

import android.view.View

abstract class MutableListItemViewHolder protected constructor(view: View): MutableListViewHolder(view) {

    abstract fun setIdEditable(isEditable: Boolean)

}