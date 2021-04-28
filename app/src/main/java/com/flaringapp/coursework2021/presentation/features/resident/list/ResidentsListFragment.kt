
package com.flaringapp.coursework2021.presentation.features.resident.list

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.databinding.FragmentResidentListBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionPickerParams
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionsDialogParent
import com.flaringapp.coursework2021.presentation.features.dialogs.options.models.ResourceOption
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParams
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParent
import com.flaringapp.coursework2021.presentation.features.resident.list.adapter.ResidentsListAdapter
import com.flaringapp.coursework2021.presentation.features.resident.list.model.ResidentsListModel
import com.flaringapp.coursework2021.presentation.features.resident.modify.ModifyResidentParams
import com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour.CreateResidentBehaviour
import com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour.EditResidentBehaviour
import com.flaringapp.coursework2021.presentation.utils.postScrollToBottom
import com.flaringapp.coursework2021.presentation.utils.recycler.DividerItemDecoration
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResidentsListFragment : ModelledFragment(R.layout.fragment_resident_list),
    PermissionDialogParent, OptionsDialogParent {

    companion object {
        private const val RESIDENT_EDIT_OPTION = "option_resident_edit"
        private const val RESIDENT_DELETE_OPTION = "option_resident_delete"

        private const val RESIDENT_OPTIONS_DIALOG = "dialog_resident_options"

        private const val DELETE_RESIDENT_DIALOG = "dialog_delete_resident"
    }

    override val model: ResidentsListModel by viewModel()

    private val binding: FragmentResidentListBinding by viewBinding(FragmentResidentListBinding::bind)

    override fun initViews() = with(binding) {
        recyclerResident.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
            reverseLayout = true
        }
        recyclerResident.adapter = ResidentsListAdapter(
            { model.handleResidentOptions(it) },
            { model.createNewResident() },
        ).apply {
            setIsEditable(true)
        }
        recyclerResident.addItemDecoration(
            DividerItemDecoration(requireContext(), R.drawable.bg_separator)
        )
    }

    override fun observeModel() = with(model) {
        residentsData.observe(viewLifecycleOwner) { residents ->
            val shouldScroll = residents.size != adapterAction { itemCount }
            adapterAction { setItems(residents) }
            if (shouldScroll) binding.recyclerResident.postScrollToBottom()
        }
        addResidentData.observe(viewLifecycleOwner) { resident ->
            adapterAction { addNewItem(resident) }
            binding.recyclerResident.postScrollToBottom()
        }
        updateResidentData.observe(viewLifecycleOwner) { resident ->
            adapterAction { updateItem(resident) }
        }
        deleteResidentData.observe(viewLifecycleOwner) { id ->
            adapterAction { removeItem(id) }
        }

        openResidentActionsData.observe(viewLifecycleOwner) {
            openResidentOptions()
        }

        openConfirmDeleteResidentData.observe(viewLifecycleOwner) { residentNameSurname ->
            openConfirmDeleteResident(residentNameSurname)
        }

        openCreateResidentData.observe(viewLifecycleOwner) {
            openCreateResident(it)
        }
        openEditResidentData.observe(viewLifecycleOwner) { resident ->
            openEditResident(resident)
        }
    }

    override fun onPermissionPositive(tag: String?) {
        if (tag == DELETE_RESIDENT_DIALOG) {
            model.confirmDeleteSelectedResident()
        }
    }

    override fun onOptionSelected(tag: String?, optionKey: String) {
        if (tag == RESIDENT_OPTIONS_DIALOG) {
            when (optionKey) {
                RESIDENT_EDIT_OPTION -> model.editSelectedResident()
                RESIDENT_DELETE_OPTION -> model.deleteSelectedResident()
            }
        }
    }

    private fun <T> adapterAction(action: ResidentsListAdapter.() -> T): T {
        return (binding.recyclerResident.adapter as ResidentsListAdapter).action()
    }

    private fun openResidentOptions() {
        OptionPickerParams.Builder()
            .withTitle(R.string.title_resident_options)
            .withOptions(
                ResourceOption(RESIDENT_EDIT_OPTION, R.string.option_resident_edit),
                ResourceOption(RESIDENT_DELETE_OPTION, R.string.option_resident_delete),
            )
            .buildAndCreate()
            .show(childFragmentManager, RESIDENT_OPTIONS_DIALOG)
    }

    private fun openConfirmDeleteResident(residentNameSurname: String) {
        PermissionDialogParams.Builder()
            .withTitle(R.string.title_resident_delete)
            .withMessage(R.string.message_resident_delete, residentNameSurname)
            .withYesNoActions()
            .buildAndCreateDialog()
            .show(childFragmentManager, DELETE_RESIDENT_DIALOG)
    }

    private fun openCreateResident(buildingId: String?) {
        goToModifyResident(
            ModifyResidentParams(buildingId, CreateResidentBehaviour()),
            getString(R.string.title_add_resident)
        )
    }

    private fun openEditResident(resident: Resident) {
        goToModifyResident(
            ModifyResidentParams(resident.buildingId, EditResidentBehaviour(resident)),
            getString(R.string.title_edit_resident)
        )
    }

    private fun goToModifyResident(params: ModifyResidentParams, title: String) {
        val direction = ResidentsListFragmentDirections.actionResidentsListToModifyResident(
            params,
            title
        )
        findNavController().navigate(direction)
    }

}