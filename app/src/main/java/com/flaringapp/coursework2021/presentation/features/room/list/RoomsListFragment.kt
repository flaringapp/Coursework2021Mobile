package com.flaringapp.coursework2021.presentation.features.room.list

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.databinding.FragmentRoomsListBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionPickerParams
import com.flaringapp.coursework2021.presentation.features.dialogs.options.OptionsDialogParent
import com.flaringapp.coursework2021.presentation.features.dialogs.options.models.ResourceOption
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParams
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParent
import com.flaringapp.coursework2021.presentation.features.room.list.adapter.RoomsListAdapter
import com.flaringapp.coursework2021.presentation.features.room.list.model.RoomsListModel
import com.flaringapp.coursework2021.presentation.features.room.modify.ModifyRoomParams
import com.flaringapp.coursework2021.presentation.features.room.modify.behaviour.CreateRoomBehaviour
import com.flaringapp.coursework2021.presentation.features.room.modify.behaviour.EditRoomBehaviour
import com.flaringapp.coursework2021.presentation.utils.recycler.DividerItemDecoration
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomsListFragment : ModelledFragment(R.layout.fragment_rooms_list),
    PermissionDialogParent, OptionsDialogParent {

    companion object {
        private const val ROOM_EDIT_OPTION = "option_room_edit"
        private const val ROOM_DELETE_OPTION = "option_room_delete"

        private const val ROOM_OPTIONS_DIALOG = "dialog_room_options"

        private const val DELETE_ROOM_DIALOG = "dialog_delete_room"
    }

    override val model: RoomsListModel by viewModel()

    private val binding: FragmentRoomsListBinding by viewBinding(FragmentRoomsListBinding::bind)

    private val args by navArgs<RoomsListFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.initBuildingId(args.buildingId)
        model.loadRooms()
    }

    override fun initViews() = with(binding) {
        recyclerRooms.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
            reverseLayout = true
        }
        recyclerRooms.adapter = RoomsListAdapter(
            { model.createNewRoom() },
            { model.handleRoomOptions(it) }
        ).apply {
            setIsEditable(true)
        }
        recyclerRooms.addItemDecoration(
            DividerItemDecoration(requireContext(), R.drawable.bg_separator)
        )
    }

    override fun observeModel() = with(model) {
        roomsData.observe(viewLifecycleOwner) { rooms ->
            adapterAction { setItems(rooms) }
        }
        addRoomData.observe(viewLifecycleOwner) { room ->
            adapterAction { addNewItem(room) }
        }
        updateRoomData.observe(viewLifecycleOwner) { room ->
            adapterAction { updateItem(room) }
        }
        deleteRoomData.observe(viewLifecycleOwner) { id ->
            adapterAction { removeItem(id) }
        }

        openRoomActionsData.observe(viewLifecycleOwner) {
            openRoomOptions()
        }

        openConfirmDeleteRoomData.observe(viewLifecycleOwner) { roomName ->
            openConfirmDeleteRoom(roomName)
        }

        openCreateRoomData.observe(viewLifecycleOwner) { buildingId ->
            openCreateRoom(buildingId)
        }
        openEditRoomData.observe(viewLifecycleOwner) { room ->
            openEditRoom(room)
        }
    }

    override fun onPermissionPositive(tag: String?) {
        if (tag == DELETE_ROOM_DIALOG) {
            model.confirmDeleteSelectedRoom()
        }
    }

    override fun onOptionSelected(tag: String?, optionKey: String) {
        if (tag == ROOM_OPTIONS_DIALOG) {
            when (optionKey) {
                ROOM_EDIT_OPTION -> model.editSelectedRoom()
                ROOM_DELETE_OPTION -> model.deleteSelectedRoom()
            }
        }
    }

    private fun <T> adapterAction(action: RoomsListAdapter.() -> T): T {
        return (binding.recyclerRooms.adapter as RoomsListAdapter).action()
    }

    private fun openRoomOptions() {
        OptionPickerParams.Builder()
            .withTitle(R.string.title_room_options)
            .withOptions(
                ResourceOption(ROOM_EDIT_OPTION, R.string.option_room_edit),
                ResourceOption(ROOM_DELETE_OPTION, R.string.option_room_delete),
            )
            .buildAndCreate()
            .show(childFragmentManager, ROOM_OPTIONS_DIALOG)
    }

    private fun openConfirmDeleteRoom(roomName: String) {
        PermissionDialogParams.Builder()
            .withTitle(R.string.title_room_delete)
            .withMessage(R.string.message_room_delete, roomName)
            .withYesNoActions()
            .buildAndCreateDialog()
            .show(childFragmentManager, DELETE_ROOM_DIALOG)
    }

    private fun openCreateRoom(buildingId: String) {
        goToModifyRoom(
            ModifyRoomParams(buildingId, CreateRoomBehaviour()),
            getString(R.string.title_create_room)
        )
    }

    private fun openEditRoom(room: Room) {
        goToModifyRoom(
            ModifyRoomParams(room.buildingId, EditRoomBehaviour(room)),
            getString(R.string.title_edit_room)
        )
    }

    private fun goToModifyRoom(params: ModifyRoomParams, title: String) {
        val direction = RoomsListFragmentDirections.actionRoomsListToModifyRoom(
            params,
            title
        )
        findNavController().navigate(direction)
    }

}