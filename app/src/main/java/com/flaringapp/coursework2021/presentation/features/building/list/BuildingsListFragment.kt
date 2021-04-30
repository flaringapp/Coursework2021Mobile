package com.flaringapp.coursework2021.presentation.features.building.list

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.databinding.FragmentBuildingsListBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.building.list.adapter.BuildingsListAdapter
import com.flaringapp.coursework2021.presentation.features.building.list.model.BuildingsListModel
import com.flaringapp.coursework2021.presentation.features.building.modify.ModifyBuildingParams
import com.flaringapp.coursework2021.presentation.features.building.modify.behaviour.CreateBuildingBehaviour
import com.flaringapp.coursework2021.presentation.features.building.modify.behaviour.EditBuildingBehaviour
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionPickerParams
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionsDialogParent
import com.flaringapp.coursework2021.presentation.features.dialogs.options.models.ResourceOption
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParams
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParent
import com.flaringapp.coursework2021.presentation.utils.postScrollToBottom
import com.flaringapp.coursework2021.presentation.utils.postSmoothScrollToBottom
import com.flaringapp.coursework2021.presentation.utils.recycler.DividerItemDecoration
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuildingsListFragment : ModelledFragment(R.layout.fragment_buildings_list),
    PermissionDialogParent, OptionsDialogParent {

    companion object {
        private const val BUILDING_EDIT_OPTION = "option_building_edit"
        private const val BUILDING_DELETE_OPTION = "option_building_delete"

        private const val BUILDING_OPTIONS_DIALOG = "dialog_building_options"

        private const val DELETE_BUILDING_DIALOG = "dialog_delete_building"
    }

    override val model: BuildingsListModel by viewModel()

    private val binding: FragmentBuildingsListBinding by viewBinding(FragmentBuildingsListBinding::bind)

    override fun initViews() = with(binding) {
        recyclerBuildings.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
            reverseLayout = true
        }
        recyclerBuildings.adapter = BuildingsListAdapter(
            { openBuildingRooms(it) },
            { model.handleBuildingOptions(it) },
            { model.createNewBuilding() },
        ).apply {
            setIsEditable(true)
        }
        recyclerBuildings.addItemDecoration(
            DividerItemDecoration(requireContext(), R.drawable.bg_separator)
        )
    }

    override fun observeModel() = with(model) {
        buildingsData.observe(viewLifecycleOwner) { buildings ->
            val shouldScroll = buildings.size != adapterAction { itemCount }
            adapterAction { setItems(buildings) }
            if (shouldScroll) binding.recyclerBuildings.postScrollToBottom()
        }
        addBuildingData.observe(viewLifecycleOwner) { building ->
            adapterAction { addNewItem(building) }
            binding.recyclerBuildings.postSmoothScrollToBottom()
        }
        updateBuildingData.observe(viewLifecycleOwner) { building ->
            adapterAction { updateItem(building) }
        }
        deleteBuildingData.observe(viewLifecycleOwner) { id ->
            adapterAction { removeItem(id) }
        }

        openBuildingActionsData.observe(viewLifecycleOwner) {
            openBuildingOptions()
        }

        openConfirmDeleteBuildingData.observe(viewLifecycleOwner) { buildingName ->
            openConfirmDeleteBuilding(buildingName)
        }

        openCreateBuildingData.observe(viewLifecycleOwner) {
            openCreateBuilding()
        }
        openEditBuildingData.observe(viewLifecycleOwner) { building ->
            openEditBuilding(building)
        }
    }

    override fun onPermissionPositive(tag: String?) {
        if (tag == DELETE_BUILDING_DIALOG) {
            model.confirmDeleteSelectedBuilding()
        }
    }

    override fun onOptionSelected(tag: String?, optionKey: String) {
        if (tag == BUILDING_OPTIONS_DIALOG) {
            when (optionKey) {
                BUILDING_EDIT_OPTION -> model.editSelectedBuilding()
                BUILDING_DELETE_OPTION -> model.deleteSelectedBuilding()
            }
        }
    }

    private fun <T> adapterAction(action: BuildingsListAdapter.() -> T): T {
        return (binding.recyclerBuildings.adapter as BuildingsListAdapter).action()
    }

    private fun openBuildingRooms(id: String) {
        val direction = BuildingsListFragmentDirections.actionBuildingsToRooms(id)
        findNavController().navigate(direction)
    }

    private fun openBuildingOptions() {
        OptionPickerParams.Builder()
            .withTitle(R.string.title_building_options)
            .withOptions(
                ResourceOption(BUILDING_EDIT_OPTION, R.string.option_building_edit),
                ResourceOption(BUILDING_DELETE_OPTION, R.string.option_building_delete),
            )
            .buildAndCreate()
            .show(childFragmentManager, BUILDING_OPTIONS_DIALOG)
    }

    private fun openConfirmDeleteBuilding(buildingName: String) {
        PermissionDialogParams.Builder()
            .withTitle(R.string.title_building_delete)
            .withMessage(R.string.message_building_delete, buildingName)
            .withYesNoActions()
            .buildAndCreateDialog()
            .show(childFragmentManager, DELETE_BUILDING_DIALOG)
    }

    private fun openCreateBuilding() {
        goToModifyBuilding(
            ModifyBuildingParams(CreateBuildingBehaviour()),
            getString(R.string.title_create_building)
        )
    }

    private fun openEditBuilding(building: Building) {
        goToModifyBuilding(
            ModifyBuildingParams(EditBuildingBehaviour(building)),
            getString(R.string.title_edit_building)
        )
    }

    private fun goToModifyBuilding(params: ModifyBuildingParams, title: String) {
        val direction = BuildingsListFragmentDirections.actionBuildingsListToModifyBuilding(
            params,
            title
        )
        findNavController().navigate(direction)
    }

}