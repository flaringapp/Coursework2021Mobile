package com.flaringapp.coursework2021.presentation.features.dialogs.options.models

import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize


@Parcelize
data class NamedOption(
    override val key: String,
    private val name: String
) : Option {

    override fun resolveName(textProvider: TextProvider) = name

}