package com.flaringapp.coursework2021.presentation.features.dialogs.options

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.databinding.DialogOptionsBinding
import com.flaringapp.coursework2021.presentation.base.BaseDialog
import com.flaringapp.coursework2021.presentation.base.parentAsListener
import com.flaringapp.coursework2021.presentation.features.dialogs.options.adapter.OptionsAdapter
import com.flaringapp.coursework2021.presentation.features.dialogs.options.models.Option
import com.flaringapp.coursework2021.presentation.features.dialogs.options.viewdata.OptionViewData
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject

class OptionsDialog : BaseDialog(R.layout.dialog_options) {

    companion object {
        private const val PARAMS_KEY = "key_params"

        @JvmStatic
        fun create(params: OptionPickerParams) = OptionsDialog().apply {
            arguments = Bundle().apply {
                putParcelable(PARAMS_KEY, params)
            }
        }
    }

    private val binding: DialogOptionsBinding by viewBinding(DialogOptionsBinding::bind)

    private val textProvider: TextProvider by inject()

    private val listener: OptionsDialogParent?
        get() = parentAsListener()

    override fun initViews() {
        val params = requireArguments().getParcelable<OptionPickerParams>(PARAMS_KEY)!!
        binding.recyclerViewOptions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = OptionsAdapter(
                params.title?.resolveText(textProvider),
                params.message?.resolveText(textProvider),
                params.options.map { it.toViewData() }
            ) { handleOptionSelected(it) }
        }
    }

    private fun handleOptionSelected(id: String) {
        listener?.onOptionSelected(tag, id)
    }

    private fun Option.toViewData() = OptionViewData(
        key,
        resolveName(textProvider)
    )
}