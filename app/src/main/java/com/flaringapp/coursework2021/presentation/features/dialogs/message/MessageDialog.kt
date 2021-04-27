package com.flaringapp.coursework2021.presentation.features.dialogs.message

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.databinding.DialogMessageBinding
import com.flaringapp.coursework2021.presentation.base.BaseDialog
import com.flaringapp.coursework2021.presentation.base.parentAsListenerOrNull
import com.flaringapp.coursework2021.presentation.utils.textWithVisibility
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject

open class MessageDialog : BaseDialog(R.layout.dialog_message) {

    companion object {
        private const val PARAMS_KEY = "key_params"

        @JvmStatic
        fun create(
            params: MessageDialogParams
        ) = MessageDialog().apply {
            arguments = Bundle().apply {
                putParcelable(PARAMS_KEY, params)
            }
        }
    }

    private val binding: DialogMessageBinding by viewBinding(DialogMessageBinding::bind)

    private val textProvider: TextProvider by inject()

    private val listener: MessageDialogParent?
        get() = parentAsListenerOrNull()

    private var isActionNotified = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val params = requireArguments().getParcelable<MessageDialogParams>(PARAMS_KEY)!!
        setTitle(params.title?.resolveText(textProvider))
        setMessage(params.message.resolveText(textProvider))
        setActionText(params.action.resolveText(textProvider))
    }

    override fun initViews() {
        binding.buttonPositive.setOnClickListener {
            dismiss()
            notifyClose(close = true)
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        notifyClose(close = false)
    }

    private fun setTitle(header: CharSequence?) {
        binding.textHeader.textWithVisibility = header
    }

    private fun setMessage(message: CharSequence) {
        binding.textMessage.text = message
    }

    private fun setActionText(text: CharSequence) {
        binding.buttonPositive.text = text
    }

    private fun notifyClose(close: Boolean) {
        if (isActionNotified) return
        isActionNotified = true
        if (close) {
            dismiss()
        }
        listener?.onMessageClosed(tag)
    }
}