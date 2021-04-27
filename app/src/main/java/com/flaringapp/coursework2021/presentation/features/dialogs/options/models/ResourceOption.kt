package com.flaringapp.coursework2021.presentation.features.dialogs.options.models

import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class ResourceOption(
    override val key: String,
    private val stringRes: Int
): Option {

    override fun resolveName(textProvider: TextProvider): CharSequence {
        return textProvider.getString(stringRes)
    }
}