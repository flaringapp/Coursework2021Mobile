package com.flaringapp.coursework2021.presentation.features.menu

import android.content.Intent
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentMenuBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.login.LoginActivity
import com.flaringapp.coursework2021.presentation.features.menu.behaviour.display.MenuDisplay
import com.flaringapp.coursework2021.presentation.features.menu.model.MenuModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : ModelledFragment(R.layout.fragment_menu) {

    override val model: MenuModel by viewModel()

    private val binding: FragmentMenuBinding by viewBinding(FragmentMenuBinding::bind)

    override fun initViews() = with(binding) {
        layoutBuildings.setOnClickListener {
            openBuildings()
        }
        layoutManagers.setOnClickListener {
            openManagers()
        }
        layoutResidents.setOnClickListener {
            openResidents()
        }
        layoutRooms.setOnClickListener {
            model.openRooms()
        }
        layoutPayments.setOnClickListener {
            openPayments()
        }
        layoutBuildingDashboard.setOnClickListener {
            openBuildingDashboard()
        }
        layoutAdminDashboard.setOnClickListener {
            openAdminDashboard()
        }
        textLogout.setOnClickListener {
            model.logout()
        }
    }

    override fun observeModel() = with(model) {
        loginData.observe(viewLifecycleOwner) {
            openLogin()
        }
        displayData.observe(viewLifecycleOwner) { display ->
            display.setupUI()
        }
        openRoomsData.observe(viewLifecycleOwner) { buildingId ->
            openRooms(buildingId)
        }
    }

    private fun openLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun MenuDisplay.setupUI() = with(binding) {
        layoutBuildings.isVisible = showBuildings
        layoutManagers.isVisible = showManagers
        layoutBuildingsAndManager.isVisible = showBuildings || showManagers

        layoutResidents.isVisible = showResidents
        layoutRooms.isVisible = showRooms
        layoutResidentsAndRooms.isVisible = showResidents || showRooms

        layoutPayments.isVisible = showPayments
        layoutAdminDashboard.isVisible = showAdminDashboard
        layoutBuildingDashboard.isVisible = showManagerDashboard
        layoutPaymentAndDashboards.isVisible =
            showPayments || showAdminDashboard || showManagerDashboard
    }

    private fun openBuildings() {
        findNavController().navigate(R.id.action_menu_to_buildings)
    }

    private fun openManagers() {
        findNavController().navigate(R.id.action_menu_to_managers)
    }

    private fun openResidents() {
        findNavController().navigate(R.id.action_menu_to_residents)
    }

    private fun openRooms(buildingId: String) {
        val direction = MenuFragmentDirections.actionMenuToRooms(buildingId)
        findNavController().navigate(direction)
    }

    private fun openPayments() {
        findNavController().navigate(R.id.action_menu_to_payments)
    }

    private fun openBuildingDashboard() {
    }

    private fun openAdminDashboard() {
    }


}