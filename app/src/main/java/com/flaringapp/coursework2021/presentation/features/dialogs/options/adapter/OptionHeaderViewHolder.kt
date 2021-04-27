package com.flaringapp.coursework2021.presentation.features.dialogs.options.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.databinding.ViewHolderOptionHeaderBinding
import com.flaringapp.coursework2021.presentation.utils.inflater

class OptionHeaderViewHolder private constructor(
    private val binding: ViewHolderOptionHeaderBinding
    ): OptionBaseViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): OptionHeaderViewHolder {
            return OptionHeaderViewHolder(
                ViewHolderOptionHeaderBinding.inflate(parent.inflater, parent, false)
            )
        }
    }

    fun bind(header: CharSequence) {
        binding.textViewHeader.text = header
    }
}