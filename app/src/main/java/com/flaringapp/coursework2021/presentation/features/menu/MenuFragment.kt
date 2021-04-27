package com.flaringapp.coursework2021.presentation.features.menu

import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentMenuBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.menu.model.MenuModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment: ModelledFragment(R.layout.fragment_menu) {

    override val model: MenuModel by viewModel()

    private val binding: FragmentMenuBinding by viewBinding(FragmentMenuBinding::bind)

    override fun initViews() = with(binding) {
        layoutBuildings.setOnClickListener {
            openBuildings()
        }
        layoutManagers.setOnClickListener {
            openManagers()
        }
        layoutUsers.setOnClickListener {
            openUsers()
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
    }

    private fun openBuildings() {
    }

    private fun openManagers() {
    }

    private fun openUsers() {
    }

    private fun openPayments() {
    }

    private fun openBuildingDashboard() {
    }

    private fun openAdminDashboard() {
    }

}