package com.flaringapp.coursework2021.presentation.common.textresolver

import androidx.annotation.StringRes
import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class TextResourceResolver(
    @StringRes
    private val textRes: Int
): TextResolver {

    override fun resolveText(textProvider: TextProvider): CharSequence {
        return textProvider.getString(textRes)
    }
}