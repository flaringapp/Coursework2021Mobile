package com.flaringapp.coursework2021.presentation.features.dialogs.permission

import android.content.DialogInterface
import android.os.Bundle
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.databinding.DialogPermissionBinding
import com.flaringapp.coursework2021.presentation.base.BaseDialog
import com.flaringapp.coursework2021.presentation.base.parentAsListenerOrNull
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.enums.PermissionOnCancelAction
import com.flaringapp.coursework2021.presentation.utils.textWithVisibility
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject

class PermissionDialog : BaseDialog(R.layout.dialog_permission) {

    companion object {
        private const val PARAMS_KEY = "key_params"

        @JvmStatic
        fun newInstance(
            params: PermissionDialogParams
        ) = PermissionDialog().apply {
            arguments = Bundle().apply {
                putParcelable(PARAMS_KEY, params)
            }
        }
    }

    private val binding: DialogPermissionBinding by viewBinding(DialogPermissionBinding::bind)

    private val textProvider: TextProvider by inject()

    private val listener: PermissionDialogParent?
        get() = parentAsListenerOrNull()

    private lateinit var defaultOnCancelAction: PermissionOnCancelAction

    private var isActionNotified = false

    override fun initViews() = with(binding) {
        val params = requireArguments().getParcelable<PermissionDialogParams>(PARAMS_KEY)!!
        defaultOnCancelAction = params.onCancelAction

        setTitle(params.title?.resolveText(textProvider))
        setMessage(params.message?.resolveText(textProvider))
        setPositiveText(params.positiveAction.resolveText(textProvider))
        setNegativeText(params.negativeAction.resolveText(textProvider))

        isCancelable = params.isCancelable

        buttonPositive.setOnClickListener { handlePositiveAction() }
        buttonNegative.setOnClickListener { handleNegativeAction()}
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        handleCancelled()
    }

    private fun setTitle(header: CharSequence?) {
        binding.textHeader.textWithVisibility = header
    }

    private fun setMessage(message: CharSequence?) {
        binding.textMessage.textWithVisibility = message
    }

    private fun setPositiveText(text: CharSequence) {
        binding.buttonPositive.text = text
    }

    private fun setNegativeText(text: CharSequence) {
        binding.buttonNegative.text = text
    }

    private fun handlePositiveAction() {
        notifyAction(close = true) { onPermissionPositive(tag) }
    }

    private fun handleNegativeAction() {
        notifyAction(close = true) { onPermissionNegative(tag) }
    }

    private fun handleCancelled() {
        notifyAction(close = false) {
            when (defaultOnCancelAction) {
                PermissionOnCancelAction.Positive -> onPermissionPositive(tag)
                PermissionOnCancelAction.Negative -> onPermissionNegative(tag)
                else -> {
                }
            }
        }
    }

    private fun notifyAction(close: Boolean, action: PermissionDialogParent.() -> Unit) {
        if (isActionNotified) return
        isActionNotified = true
        if (close) {
            dismiss()
        }
        listener?.action()
    }
}