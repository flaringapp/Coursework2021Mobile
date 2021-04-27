package com.flaringapp.coursework2021.presentation.features.dialogs.options.adapter

import android.view.ViewGroup
import com.flaringapp.coursework2021.databinding.ViewHolderOptionBinding
import com.flaringapp.coursework2021.presentation.features.dialogs.options.viewdata.OptionViewData
import com.flaringapp.coursework2021.presentation.utils.inflater

class OptionViewHolder private constructor(
    private val binding: ViewHolderOptionBinding
) : OptionBaseViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): OptionViewHolder {
            return OptionViewHolder(
                ViewHolderOptionBinding.inflate(parent.inflater, parent, false)
            )
        }
    }

    fun bind(option: OptionViewData, clickBlock: (String) -> Unit) = with(binding) {
        textViewOption.text = option.text
        layoutOption.setOnClickListener { clickBlock(option.id) }
    }

    fun release() {
        binding.layoutOption.setOnClickListener(null)
    }
}