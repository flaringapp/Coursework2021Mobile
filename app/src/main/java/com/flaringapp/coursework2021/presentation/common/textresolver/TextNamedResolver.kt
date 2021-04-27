package com.flaringapp.coursework2021.presentation.common.textresolver

import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class TextNamedResolver(
    private val name: CharSequence
): TextResolver {
    override fun resolveText(textProvider: TextProvider): CharSequence = name
}