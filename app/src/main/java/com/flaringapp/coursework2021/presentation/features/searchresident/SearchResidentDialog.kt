package com.flaringapp.coursework2021.presentation.features.searchresident

import android.os.Bundle
import android.widget.ArrayAdapter
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.databinding.DialogSearchResidentBinding
import com.flaringapp.coursework2021.presentation.base.ModelledDialog
import com.flaringapp.coursework2021.presentation.base.parentAsListener
import com.flaringapp.coursework2021.presentation.features.searchresident.model.SearchResidentModel
import com.flaringapp.coursework2021.presentation.features.searchresident.models.SearchResidentViewData
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResidentDialog : ModelledDialog(R.layout.dialog_search_resident) {

    companion object {
        private const val ROOM_NAME_KEY = "key_room_name"

        fun create(roomName: String) = SearchResidentDialog().apply {
            arguments = Bundle().apply {
                putString(ROOM_NAME_KEY, roomName)
            }
        }
    }

    override val model: SearchResidentModel by viewModel()

    private val binding: DialogSearchResidentBinding by viewBinding(DialogSearchResidentBinding::bind)

    private val listener: SearchResidentParent
        get() = parentAsListener()

    private var isActionNotified: Boolean = false

    override fun initViews() {
        val roomName = requireArguments().getString(ROOM_NAME_KEY)
        binding.textRoomName.text = roomName
    }

    override fun observeModel() = with(model) {
        residentsData.observe(viewLifecycleOwner) { residents ->
            setupResidents(residents)
        }

        searchResultData.observe(viewLifecycleOwner) { resident ->
            notifyResidentSelected(resident)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupResidents(residents: List<SearchResidentViewData>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, residents.map { it.name })
        binding.inputName.setAdapter(adapter)
        binding.inputName.setOnItemClickListener { _, _, position, _ ->
            model.handleResidentSelected(
                residents.find { it.name == adapter.getItem(position) }!!.id
            )
        }
        binding.inputName.showDropDown()
    }

    private fun notifyResidentSelected(resident: Resident) {
        if (isActionNotified) return
        isActionNotified = true
        listener.onSearchResidentSelected(resident)
        dismiss()
    }
}