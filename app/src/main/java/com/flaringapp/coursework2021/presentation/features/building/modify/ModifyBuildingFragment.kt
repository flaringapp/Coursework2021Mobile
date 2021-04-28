package com.flaringapp.coursework2021.presentation.features.building.modify

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentBuildingModifyBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.building.modify.model.ModifyBuildingModel
import com.flaringapp.coursework2021.presentation.utils.common.isVisibleAndAnimateProgressBar
import com.flaringapp.coursework2021.presentation.utils.doOnDoneClicked
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModifyBuildingFragment : ModelledFragment(R.layout.fragment_building_modify) {

    override val model: ModifyBuildingModel by viewModel()

    private val args by navArgs<ModifyBuildingFragmentArgs>()

    private val binding: FragmentBuildingModifyBinding by viewBinding(FragmentBuildingModifyBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.initBehaviour(args.params.behaviour)
    }

    override fun initViews() = with(binding) {
        inputName.doAfterTextChanged {
            model.handleNameChanged(it.toString())
        }
        inputDescription.doAfterTextChanged {
            model.handleDescriptionChanged(it.toString())
        }
        inputLatitude.doAfterTextChanged {
            model.handleLatitudeChanged(it.toString())
        }
        inputLongitude.doAfterTextChanged {
            model.handleLongitudeChanged(it.toString())
        }
        inputAddress.doAfterTextChanged {
            model.handleAddressChanged(it.toString())
        }
        inputArea.doAfterTextChanged {
            model.handleAreaChanged(it.toString())
        }
        inputArea.doOnDoneClicked {
            buttonSubmit.performClick()
        }
        buttonSubmit.setOnClickListener {
            model.saveBuilding()
        }
    }

    override fun observeModel() = with(model) {
        nameData.observe(viewLifecycleOwner) { name ->
            binding.inputName.setText(name)
        }
        descriptionData.observe(viewLifecycleOwner) { description ->
            binding.inputDescription.setText(description)
        }
        latitudeData.observe(viewLifecycleOwner) { latitude ->
            binding.inputLatitude.setText(latitude)
        }
        longitudeData.observe(viewLifecycleOwner) { longitude ->
            binding.inputLongitude.setText(longitude)
        }
        addressData.observe(viewLifecycleOwner) { address ->
            binding.inputAddress.setText(address)
        }
        areaData.observe(viewLifecycleOwner) { area ->
            binding.inputArea.setText(area)
        }

        nameErrorData.observe(viewLifecycleOwner) { nameError ->
            binding.layoutInputName.error = nameError?.let { getString(it) }
        }
        latitudeErrorData.observe(viewLifecycleOwner) { latitudeError ->
            binding.layoutInputLatitude.error = latitudeError?.let { getString(it) }
        }
        longitudeErrorData.observe(viewLifecycleOwner) { longitudeError ->
            binding.layoutInputLongitude.error = longitudeError?.let { getString(it) }
        }
        areaErrorData.observe(viewLifecycleOwner) { areaError ->
            binding.layoutInputArea.error = areaError?.let { getString(it) }
        }

        loadingData.observe(viewLifecycleOwner) { isLoading ->
            setIsLoading(isLoading)
        }

        closeScreenData.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        binding.progressBar.isVisibleAndAnimateProgressBar = isLoading

        binding.buttonSubmit.isEnabled = !isLoading
        binding.buttonSubmit.isVisibleAndAnimateProgressBar = !isLoading

        listOf(
            binding.layoutInputName,
            binding.layoutInputDescription,
            binding.layoutInputLatitude,
            binding.layoutInputLongitude,
            binding.layoutInputAddress,
            binding.layoutInputArea,
        ).forEach { it.isEnabled = !isLoading }
    }

}