package com.flaringapp.coursework2021.presentation.features.dialogs.options

import android.os.Parcelable
import androidx.annotation.StringRes
import com.flaringapp.coursework2021.presentation.common.textresolver.TextNamedResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextResourceResolver
import com.flaringapp.coursework2021.presentation.features.dialogs.options.models.Option
import kotlinx.parcelize.Parcelize

@Parcelize
class OptionPickerParams(
    val title: TextResolver?,
    val message: TextResolver?,
    val options: List<Option>
): Parcelable {

    fun create() = OptionsDialog.create(this)

    class Builder {
        private var title: TextResolver? = null
        private var message: TextResolver? = null
        private var options: List<Option> = emptyList()

        fun withTitle(resolver: TextResolver) = apply {
            this.title = resolver
        }

        fun withTitle(name: String) = apply {
            this.title = TextNamedResolver(name)
        }

        fun withTitle(@StringRes res: Int) = apply {
            this.title = TextResourceResolver(res)
        }

        fun withMessage(resolver: TextResolver) = apply {
            this.message = resolver
        }

        fun withMessage(name: String) = apply {
            this.message = TextNamedResolver(name)
        }

        fun withMessage(@StringRes res: Int) = apply {
            this.message = TextResourceResolver(res)
        }

        fun withOptions(options: List<Option>) = apply {
            this.options = options
        }

        fun withOptionsArray(options: Array<Option>) = apply {
            this.options = options.toList()
        }

        fun withOptions(vararg options: Option) = apply {
            this.options = options.toList()
        }

        fun build() = OptionPickerParams(title, message, options)

        fun buildAndCreate() = build().create()
    }

}