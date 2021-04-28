
package com.flaringapp.coursework2021.presentation.features.resident.modify

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentResidentModifyBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.resident.modify.model.ModifyResidentModel
import com.flaringapp.coursework2021.presentation.utils.common.isVisibleAndAnimateProgressBar
import com.flaringapp.coursework2021.presentation.utils.doOnDoneClicked
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModifyResidentFragment : ModelledFragment(R.layout.fragment_resident_modify) {

    override val model: ModifyResidentModel by viewModel()

    private val args by navArgs<ModifyResidentFragmentArgs>()

    private val binding: FragmentResidentModifyBinding by viewBinding(FragmentResidentModifyBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.init(args.params.buildingId, args.params.behaviour)
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
        inputDescription.doAfterTextChanged {
            model.handleDescriptionChanged(it.toString())
        }

        inputDescription.doOnDoneClicked {
            buttonSubmit.performClick()
        }

        buttonSubmit.setOnClickListener {
            model.saveResident()
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

        nameErrorData.observe(viewLifecycleOwner) { nameError ->
            binding.layoutInputName.error = nameError?.let { getString(it) }
        }
        surnameErrorData.observe(viewLifecycleOwner) { surnameError ->
            binding.inputSurname.error = surnameError?.let { getString(it) }
        }
        emailErrorData.observe(viewLifecycleOwner) { emailError ->
            binding.inputEmail.error = emailError?.let { getString(it) }
        }

        loadingData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisibleAndAnimateProgressBar = isLoading
            binding.buttonSubmit.isEnabled = !isLoading
            binding.buttonSubmit.isVisibleAndAnimateProgressBar = !isLoading
            listOf(
                binding.inputName,
                binding.inputSurname,
                binding.inputEmail,
                binding.inputDescription,
            ).forEach { it.isEnabled = !isLoading }
        }

        closeScreenData.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

}