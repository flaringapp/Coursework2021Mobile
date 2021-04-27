package com.flaringapp.coursework2021.presentation.features.dialogs.options.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.databinding.ViewHolderOptionMessageBinding
import com.flaringapp.coursework2021.presentation.utils.inflater

class OptionMessageViewHolder private constructor(
    private val binding: ViewHolderOptionMessageBinding
): OptionBaseViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): OptionMessageViewHolder {
            return OptionMessageViewHolder(
                ViewHolderOptionMessageBinding.inflate(parent.inflater, parent, false)
            )
        }
    }

    fun bind(header: CharSequence) {
        binding.textViewMessage.text = header
    }
}