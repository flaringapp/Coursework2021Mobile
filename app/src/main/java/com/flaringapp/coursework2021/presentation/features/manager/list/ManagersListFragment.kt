package com.flaringapp.coursework2021.presentation.features.manager.list

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import com.flaringapp.coursework2021.databinding.FragmentManagerListBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionPickerParams
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionsDialogParent
import com.flaringapp.coursework2021.presentation.features.dialogs.options.models.ResourceOption
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParams
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParent
import com.flaringapp.coursework2021.presentation.features.manager.list.adapter.ManagerListAdapter
import com.flaringapp.coursework2021.presentation.features.manager.list.model.ManagersListModel
import com.flaringapp.coursework2021.presentation.features.manager.modify.ModifyManagerParams
import com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour.CreateManagerBehaviour
import com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour.EditManagerBehaviour
import com.flaringapp.coursework2021.presentation.utils.postScrollToBottom
import com.flaringapp.coursework2021.presentation.utils.recycler.DividerItemDecoration
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManagersListFragment : ModelledFragment(R.layout.fragment_manager_list),
    PermissionDialogParent, OptionsDialogParent {

    companion object {
        private const val MANAGER_EDIT_OPTION = "option_manager_edit"
        private const val MANAGER_DELETE_OPTION = "option_manager_delete"

        private const val MANAGER_OPTIONS_DIALOG = "dialog_manager_options"

        private const val DELETE_MANAGER_DIALOG = "dialog_delete_manager"
    }

    override val model: ManagersListModel by viewModel()

    private val binding: FragmentManagerListBinding by viewBinding(FragmentManagerListBinding::bind)

    override fun initViews() = with(binding) {
        recyclerManagers.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
            reverseLayout = true
        }
        recyclerManagers.adapter = ManagerListAdapter(
            { model.handleManagerOptions(it) },
            { model.createNewManager() },
        ).apply {
            setIsEditable(true)
        }
        recyclerManagers.addItemDecoration(
            DividerItemDecoration(requireContext(), R.drawable.bg_separator)
        )
    }

    override fun observeModel() = with(model) {
        managersData.observe(viewLifecycleOwner) { managers ->
            val shouldScroll = managers.size != adapterAction { itemCount }
            adapterAction { setItems(managers) }
            if (shouldScroll) binding.recyclerManagers.postScrollToBottom()
        }
        addManagerData.observe(viewLifecycleOwner) { manager ->
            adapterAction { addNewItem(manager) }
            binding.recyclerManagers.postScrollToBottom()
        }
        updateManagerData.observe(viewLifecycleOwner) { manager ->
            adapterAction { updateItem(manager) }
        }
        deleteManagerData.observe(viewLifecycleOwner) { id ->
            adapterAction { removeItem(id) }
        }

        openManagerActionsData.observe(viewLifecycleOwner) {
            openManagerOptions()
        }

        openConfirmDeleteManagerData.observe(viewLifecycleOwner) { managerNameSurname ->
            openConfirmDeleteManager(managerNameSurname)
        }

        openCreateManagerData.observe(viewLifecycleOwner) {
            openCreateManager()
        }
        openEditManagerData.observe(viewLifecycleOwner) { manager ->
            openEditManager(manager)
        }
    }

    override fun onPermissionPositive(tag: String?) {
        if (tag == DELETE_MANAGER_DIALOG) {
            model.confirmDeleteSelectedManager()
        }
    }

    override fun onOptionSelected(tag: String?, optionKey: String) {
        if (tag == MANAGER_OPTIONS_DIALOG) {
            when (optionKey) {
                MANAGER_EDIT_OPTION -> model.editSelectedManager()
                MANAGER_DELETE_OPTION -> model.deleteSelectedManager()
            }
        }
    }

    private fun <T> adapterAction(action: ManagerListAdapter.() -> T): T {
        return (binding.recyclerManagers.adapter as ManagerListAdapter).action()
    }

    private fun openManagerOptions() {
        OptionPickerParams.Builder()
            .withTitle(R.string.title_manager_options)
            .withOptions(
                ResourceOption(MANAGER_EDIT_OPTION, R.string.option_manager_edit),
                ResourceOption(MANAGER_DELETE_OPTION, R.string.option_manager_delete),
            )
            .buildAndCreate()
            .show(childFragmentManager, MANAGER_OPTIONS_DIALOG)
    }

    private fun openConfirmDeleteManager(managerNameSurname: String) {
        PermissionDialogParams.Builder()
            .withTitle(R.string.title_manager_delete)
            .withMessage(R.string.message_manager_delete, managerNameSurname)
            .withYesNoActions()
            .buildAndCreateDialog()
            .show(childFragmentManager, DELETE_MANAGER_DIALOG)
    }

    private fun openCreateManager() {
        goToModifyManager(
            ModifyManagerParams(CreateManagerBehaviour()),
            getString(R.string.title_create_building)
        )
    }

    private fun openEditManager(manager: Manager) {
        goToModifyManager(
            ModifyManagerParams(EditManagerBehaviour(manager)),
            getString(R.string.title_edit_building)
        )
    }

    private fun goToModifyManager(params: ModifyManagerParams, title: String) {
        val direction = ManagersListFragmentDirections.actionManagersListToModifyManager(
            params,
            title
        )
        findNavController().navigate(direction)
    }

}