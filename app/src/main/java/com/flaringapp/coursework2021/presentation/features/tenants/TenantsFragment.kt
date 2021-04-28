package com.flaringapp.coursework2021.presentation.features.tenants

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.databinding.FragmentTenantsBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParams
import com.flaringapp.coursework2021.presentation.features.dialogs.permission.PermissionDialogParent
import com.flaringapp.coursework2021.presentation.features.searchresident.SearchResidentDialog
import com.flaringapp.coursework2021.presentation.features.searchresident.SearchResidentParent
import com.flaringapp.coursework2021.presentation.features.tenants.adapter.RoomTenantsAdapter
import com.flaringapp.coursework2021.presentation.features.tenants.model.TenantsModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TenantsFragment : ModelledFragment(R.layout.fragment_tenants),
    SearchResidentParent,
    PermissionDialogParent {

    companion object {
        private const val SELECT_RESIDENT_DIALOG = "dialog_select_resident"

        private const val CONFIRM_DELETE_TENANT_DIALOG = "dialog_confirm_delete_tenant"
    }

    override val model: TenantsModel by viewModel()

    private val args by navArgs<TenantsFragmentArgs>()

    private val binding: FragmentTenantsBinding by viewBinding(FragmentTenantsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.init(args.params)
        model.loadData()
    }

    override fun initViews() = with(binding) {
        recyclerTenants.layoutManager = LinearLayoutManager(requireContext())
        recyclerTenants.adapter = RoomTenantsAdapter(
            { model.requestAddNewTenant() },
            { model.removeTenant(it) }
        )
    }

    override fun observeModel() = with(model) {
        tenantsData.observe(viewLifecycleOwner) { tenants ->
            adapterAction { setItems(tenants) }
        }
        canAddTenantsData.observe(viewLifecycleOwner) { canAddTenantsData ->
            adapterAction { setCanAdd(canAddTenantsData) }
        }
        tenantAddedData.observe(viewLifecycleOwner) { tenant ->
            adapterAction { addItem(tenant) }
        }
        tenantRemovedData.observe(viewLifecycleOwner) { tenantId ->
            adapterAction { removeItem(tenantId) }
        }

        openAddNewTenantData.observe(viewLifecycleOwner) { roomName ->
            openAddNewTenant(roomName)
        }
        openConfirmRemoveTenantData.observe(viewLifecycleOwner) { tenantName ->
            askToRemoveTenant(tenantName)
        }
    }

    override fun onSearchResidentSelected(resident: Resident) {
        model.addNewTenant(resident)
    }

    override fun onPermissionPositive(tag: String?) {
        if (tag == CONFIRM_DELETE_TENANT_DIALOG) {
            model.confirmRemoveTenant()
        }
    }

    private fun <T> adapterAction(action: RoomTenantsAdapter.() -> T): T {
        return (binding.recyclerTenants.adapter as RoomTenantsAdapter).action()
    }

    private fun openAddNewTenant(roomName: String) {
        SearchResidentDialog.create(roomName)
            .show(childFragmentManager, SELECT_RESIDENT_DIALOG)
    }

    private fun askToRemoveTenant(tenantName: String) {
        PermissionDialogParams.Builder()
            .withTitle(R.string.title_remove_tenant)
            .withMessage(R.string.message_remove_tenant, tenantName)
            .withYesNoActions()
            .buildAndCreateDialog()
            .show(childFragmentManager, CONFIRM_DELETE_TENANT_DIALOG)
    }

}