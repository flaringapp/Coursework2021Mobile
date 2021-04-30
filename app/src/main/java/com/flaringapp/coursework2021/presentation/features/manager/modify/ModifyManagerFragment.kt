package com.flaringapp.coursework2021.presentation.features.manager.modify

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentManagerModifyBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.manager.modify.model.ModifyManagerModel
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerBuildingViewData
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerSelectBuildingsViewData
import com.flaringapp.coursework2021.presentation.utils.common.isVisibleAndAnimateProgressBar
import com.flaringapp.coursework2021.presentation.utils.doOnDoneClicked
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModifyManagerFragment : ModelledFragment(R.layout.fragment_manager_modify) {

    override val model: ModifyManagerModel by viewModel()

    private val args by navArgs<ModifyManagerFragmentArgs>()

    private val binding: FragmentManagerModifyBinding by viewBinding(FragmentManagerModifyBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.initBehaviour(args.params.behaviour)
        model.loadData()
    }

    override fun initViews() = with(binding) {
        inputName.doAfterTextChanged {
            model.handleNameChanged(it.toString())
        }
        inputSurname.doAfterTextChanged {
            model.handleSurnameChanged(it.toString())
        }
        inputEmail.doAfterTextChanged {
            model.handleEmailChanged(it.toString())
        }
        inputPassword.doAfterTextChanged {
            model.handlePasswordChanged(it.toString())
        }
        inputDescription.doAfterTextChanged {
            model.handleDescriptionChanged(it.toString())
        }

        inputBuilding.doOnDoneClicked {
            buttonSubmit.performClick()
        }

        buttonSubmit.setOnClickListener {
            model.saveManager()
        }
    }

    override fun observeModel() = with(model) {
        nameData.observe(viewLifecycleOwner) { name ->
            binding.inputName.setText(name)
        }
        surnameData.observe(viewLifecycleOwner) { name ->
            binding.inputSurname.setText(name)
        }
        emailData.observe(viewLifecycleOwner) { name ->
            binding.inputEmail.setText(name)
        }
        descriptionData.observe(viewLifecycleOwner) { description ->
            binding.inputDescription.setText(description)
        }
        buildingsData.observe(viewLifecycleOwner) { buildingsData ->
            setupBuildingsSelected(buildingsData)
        }

        nameErrorData.observe(viewLifecycleOwner) { nameError ->
            binding.layoutInputName.error = nameError?.let { getString(it) }
        }
        surnameErrorData.observe(viewLifecycleOwner) { surnameError ->
            binding.layoutInputSurname.error = surnameError?.let { getString(it) }
        }
        emailErrorData.observe(viewLifecycleOwner) { emailError ->
            binding.layoutInputEmail.error = emailError?.let { getString(it) }
        }
        passwordErrorData.observe(viewLifecycleOwner) { passwordError ->
            binding.layoutInputPassword.error = passwordError?.let { getString(it) }
        }
        buildingErrorData.observe(viewLifecycleOwner) { buildingError ->
            binding.layoutInputBuilding.error = buildingError?.let { getString(it) }
        }

        loadingData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisibleAndAnimateProgressBar = isLoading
            binding.buttonSubmit.isEnabled = !isLoading
            binding.buttonSubmit.isVisibleAndAnimateProgressBar = !isLoading
            listOf(
                binding.layoutInputName,
                binding.layoutInputSurname,
                binding.layoutInputEmail,
                binding.layoutInputDescription,
                binding.layoutInputBuilding,
            ).forEach { it.isEnabled = !isLoading }
        }

        closeScreenData.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun setupBuildingsSelected(buildingsData: ManagerSelectBuildingsViewData) {
        setBuildingsList(buildingsData.buildings)
        binding.inputBuilding.setText(buildingsData.selectedBuildingName, false)
    }

    private fun setBuildingsList(buildings: List<ManagerBuildingViewData>) {
        val itemTexts = buildings.map { it.name }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, itemTexts)
        binding.inputBuilding.setAdapter(adapter)
        binding.inputBuilding.setOnItemClickListener { _, _, position, _ ->
            model.handleBuildingChanged(buildings[position].id)
        }
    }

}