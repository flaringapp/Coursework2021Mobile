package com.flaringapp.coursework2021.presentation.common.textresolver

import androidx.annotation.StringRes
import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class TextComplexResourceResolver(
    @StringRes
    private val textRes: Int,
    private vararg val args: @RawValue Any
) : TextResolver {
    override fun resolveText(textProvider: TextProvider): CharSequence {
        return textProvider.getText(textRes, *args)
    }
}