package com.flaringapp.coursework2021.presentation.common.textresolver

import androidx.annotation.StringRes
import com.flaringapp.coursework2021.data.text.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class TextResourceComplexParamsResolver(
    @StringRes
    private val textRes: Int,
    private vararg val args: TextResolver
) : TextResolver {

    companion object {
        fun ofResourceParams(
            @StringRes textRes: Int,
            @StringRes vararg args: Int
        ): TextResourceComplexParamsResolver {
            return TextResourceComplexParamsResolver(
                textRes,
                *args.map { TextResourceResolver(it) }.toTypedArray()
            )
        }
    }

    override fun resolveText(textProvider: TextProvider): CharSequence {
        return textProvider.getText(
            textRes,
            *args.map { it.resolveText(textProvider) }.toTypedArray()
        )
    }
}