package com.flaringapp.coursework2021.presentation.features.building.list

import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.databinding.FragmentBuildingsListBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.building.list.adapter.BuildingsListAdapter
import com.flaringapp.coursework2021.presentation.features.building.list.model.BuildingsListModel
import com.flaringapp.coursework2021.presentation.utils.recycler.DividerItemDecoration
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuildingsListFragment : ModelledFragment(R.layout.fragment_buildings_list) {

    override val model: BuildingsListModel by viewModel()

    private val binding: FragmentBuildingsListBinding by viewBinding(FragmentBuildingsListBinding::bind)

    override fun initViews() = with(binding) {
        recyclerBuildings.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
            reverseLayout = true
        }
        recyclerBuildings.adapter = BuildingsListAdapter(
            { model.createNewBuilding() },
            { model.editBuilding(it) }
        ).apply {
            setIsEditable(true)
        }
        recyclerBuildings.addItemDecoration(
            DividerItemDecoration(requireContext(), R.drawable.bg_separator)
        )
    }

    override fun observeModel() = with(model) {
        buildingsData.observe(viewLifecycleOwner) { buildings ->
            adapterAction { setItems(buildings) }
        }
        addBuildingData.observe(viewLifecycleOwner) { building ->
            adapterAction { addNewItem(building) }
        }
        updateBuildingData.observe(viewLifecycleOwner) { building ->
            adapterAction { updateItem(building) }
        }
        deleteBuildingData.observe(viewLifecycleOwner) { id ->
            adapterAction { removeItem(id) }
        }

        openCreateBuildingData.observe(viewLifecycleOwner) {
            openCreateBuilding()
        }
        openEditBuildingData.observe(viewLifecycleOwner) { building ->
            openEditBuilding(building)
        }
    }

    private fun <T> adapterAction(action: BuildingsListAdapter.() -> T): T {
        return (binding.recyclerBuildings.adapter as BuildingsListAdapter).action()
    }

    private fun openCreateBuilding() {
        // TODO open create building
    }

    private fun openEditBuilding(building: Building) {
        // TODO open edit building
    }

}