package com.flaringapp.coursework2021.presentation.features.common.adapter

import android.view.ViewGroup
import androidx.annotation.StringRes
import com.flaringapp.coursework2021.app.common.Action

abstract class SimpleMutableListAdapter<IVH : MutableListItemViewHolder, I : MutableListItem>(
    @StringRes
    private val addItemTextRes: Int,
    private val addItemAction: Action
) : MutableListAdapter<IVH, SimpleMutableListAddItemViewHolder, I>() {

    override fun createAddItemViewHolder(parent: ViewGroup): SimpleMutableListAddItemViewHolder {
        return SimpleMutableListAddItemViewHolder.create(parent)
    }

    override fun bindAddItemViewHolder(holder: SimpleMutableListAddItemViewHolder, position: Int) {
        holder.bind(addItemTextRes, addItemAction)
    }
}