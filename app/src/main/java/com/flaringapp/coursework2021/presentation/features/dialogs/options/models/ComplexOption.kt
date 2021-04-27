package com.flaringapp.coursework2021.presentation.features.dialogs.options.models

import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize


@Parcelize
class ComplexOption(
    override val key: String,
    private val resolveText: (TextProvider) -> CharSequence
): Option {

    override fun resolveName(textProvider: TextProvider): CharSequence {
        return resolveText(textProvider)
    }

}