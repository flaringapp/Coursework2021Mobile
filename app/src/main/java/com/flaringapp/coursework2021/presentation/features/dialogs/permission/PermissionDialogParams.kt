package com.flaringapp.coursework2021.presentation.features.dialogs.permission

import android.os.Parcelable
import androidx.annotation.StringRes
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.presentation.common.textresolver.TextComplexResourceResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextNamedResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextResolver
import com.flaringapp.coursework2021.presentation.common.textresolver.TextResourceResolver
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.enums.PermissionOnCancelAction
import kotlinx.parcelize.Parcelize

@Parcelize
class PermissionDialogParams(
    val title: TextResolver? = null,
    val message: TextResolver? = null,
    val positiveAction: TextResolver,
    val negativeAction: TextResolver,
    val onCancelAction: PermissionOnCancelAction,
    val isCancelable: Boolean
) : Parcelable {

    fun createDialog() = PermissionDialog.newInstance(this)

    class Builder {

        private var title: TextResolver? = null
        private var message: TextResolver? = null

        private var positiveAction: TextResolver? = null
        private var negativeAction: TextResolver? = null

        private var onCancelAction: PermissionOnCancelAction = PermissionOnCancelAction.None

        private var isCancellable = true

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

        fun withPositiveAction(@StringRes textRes: Int) = apply {
            this.positiveAction = TextResourceResolver(textRes)
        }

        fun withNegativeAction(@StringRes textRes: Int) = apply {
            this.negativeAction = TextResourceResolver(textRes)
        }

        fun withYesNoActions() = apply {
            withPositiveAction(R.string.yes)
            withNegativeAction(R.string.no)
        }

        fun withConfirmCancelActions() = apply {
            withPositiveAction(R.string.submit)
            withNegativeAction(R.string.cancel)
        }

        fun withOnCancelAction(onCancelAction: PermissionOnCancelAction) = apply {
            this.onCancelAction = onCancelAction
        }

        fun notCancelable() = apply {
            this.isCancellable = false
        }

        fun build(): PermissionDialogParams {
            require(positiveAction != null) {
                "Permission dialog positive action is required!"
            }
            require(negativeAction != null) {
                "Permission dialog positive action is required!"
            }
            return PermissionDialogParams(
                title,
                message,
                positiveAction!!,
                negativeAction!!,
                onCancelAction,
                isCancellable
            )
        }

        fun buildAndCreateDialog() = build().createDialog()
    }
}