package com.flaringapp.coursework2021.presentation.features.common.adapter

import android.view.ViewGroup
import androidx.annotation.StringRes
import com.flaringapp.coursework2021.app.common.Action
import com.flaringapp.coursework2021.databinding.ViewHolderAddItemBinding
import com.flaringapp.coursework2021.presentation.utils.inflater

class SimpleMutableListAddItemViewHolder(
    private val binding: ViewHolderAddItemBinding
) : MutableListAddItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = SimpleMutableListAddItemViewHolder(
            ViewHolderAddItemBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(@StringRes textRes: Int, addItem: Action) = with(binding) {
        root.setText(textRes)
        root.setOnClickListener { addItem() }
    }

}