package com.flaringapp.coursework2021.presentation.features.dialogs.message

import android.os.Parcelable
import androidx.annotation.StringRes
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.presentation.common.textresolver.TextComplexResourceResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextNamedResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextResourceResolver
import kotlinx.parcelize.Parcelize

@Parcelize
class MessageDialogParams(
    val title: TextResolver?,
    val message: TextResolver,
    val action: TextResolver,
    val closeDelay: Long?
) : Parcelable {

    fun createDialog() = MessageDialog.create(this)

    class Builder {

        companion object {
            private val DEFAULT_ACTION = TextResourceResolver(R.string.ok)
        }

        private var title: TextResolver? = null
        private var message: TextResolver? = null

        private var action: TextResolver = DEFAULT_ACTION

        private var closeDelay: Long? = null

        fun withTitle(resolver: TextResolver) = apply {
            this.title = resolver
        }

        fun withTitle(text: String) = apply {
            this.title = TextNamedResolver(text)
        }

        fun withTitle(@StringRes textRes: Int) = apply {
            this.title = TextResourceResolver(textRes)
        }

        fun withMessage(resolver: TextResolver) = apply {
            this.message = resolver
        }

        fun withMessage(text: String) = apply {
            this.message = TextNamedResolver(text)
        }

        fun withMessage(@StringRes textRes: Int, vararg params: Any) = apply {
            this.message = TextComplexResourceResolver(textRes, *params)
        }

        fun withMessage(@StringRes textRes: Int) = apply {
            this.message = TextResourceResolver(textRes)
        }

        fun withAction(@StringRes textRes: Int) = apply {
            this.action = TextResourceResolver(textRes)
        }

        fun withCloseDelay(delay: Long) = apply {
            this.closeDelay = delay
        }

        fun build(): MessageDialogParams {
            require(message != null) {
                "Message dialog message is required!"
            }
            return MessageDialogParams(
                title,
                message!!,
                action,
                closeDelay
            )
        }

        fun buildAndCreateDialog() = build().createDialog()
    }
}