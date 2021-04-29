package com.flaringapp.coursework2021.presentation.features.transaction.create

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentCreateTransactionBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.transaction.create.model.CreateTransactionModel
import com.flaringapp.coursework2021.presentation.features.transaction.create.models.TransactionRentalViewData
import com.flaringapp.coursework2021.presentation.features.transaction.create.models.TransactionResidentViewData
import com.flaringapp.coursework2021.presentation.features.transaction.create.models.TransactionSelectRentalViewData
import com.flaringapp.coursework2021.presentation.features.transaction.create.models.TransactionSelectResidentViewData
import com.flaringapp.coursework2021.presentation.utils.common.isVisibleAndAnimateProgressBar
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateTransactionFragment : ModelledFragment(R.layout.fragment_create_transaction) {

    override val model: CreateTransactionModel by viewModel()

    private val binding: FragmentCreateTransactionBinding by viewBinding(
        FragmentCreateTransactionBinding::bind
    )

    override fun initViews() = with(binding) {
        pickerWorkplacesCount.onAmountChange = { monthsCount ->
            model.handleMonthCountChanged(monthsCount)
        }
        buttonSubmit.setOnClickListener {
            model.createTransaction()
        }
    }

    override fun observeModel() = with(model) {
        residentsData.observe(viewLifecycleOwner) { residents ->
            setupResidents(residents)
        }
        rentalsData.observe(viewLifecycleOwner) { rentals ->
            setupRentals(rentals)
        }
        monthsCountData.observe(viewLifecycleOwner) { monthsCount ->
            binding.pickerWorkplacesCount.setSelectedAmount(monthsCount)
        }

        residentErrorData.observe(viewLifecycleOwner) { residentError ->
            binding.layoutInputRental.error = residentError?.let { getString(it) }
        }
        rentalErrorData.observe(viewLifecycleOwner) { rentalError ->
            binding.layoutInputRental.error = rentalError?.let { getString(it) }
        }

        residentsAvailableData.observe(viewLifecycleOwner) { residentsAvailable ->
            binding.layoutInputResident.isEnabled = residentsAvailable
            binding.inputResident.isEnabled = residentsAvailable
        }
        rentalsAvailableData.observe(viewLifecycleOwner) { rentalsAvailable ->
            binding.layoutInputRental.isEnabled = rentalsAvailable
            binding.inputRental.isEnabled = rentalsAvailable
        }

        priceData.observe(viewLifecycleOwner) { price ->
            binding.textMoneyAmount.text = price
        }

        loadingData.observe(viewLifecycleOwner) { isLoading ->
            setIsLoading(isLoading)
        }

        closeScreenData.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun setupResidents(data: TransactionSelectResidentViewData) {
        setResidentsList(data.residents)
        binding.inputResident.setText(data.selectedResidentName, false)
    }

    private fun setResidentsList(residents: List<TransactionResidentViewData>) {
        binding.inputResident.setItemsList(
            residents,
            { it.name },
            { model.handleResidentChanged(it.id) }
        )
    }

    private fun setupRentals(data: TransactionSelectRentalViewData) {
        setRentalsList(data.rentals)
        binding.inputRental.setText(data.selectedRentalName, false)
    }

    private fun setRentalsList(rentals: List<TransactionRentalViewData>) {
        binding.inputRental.setItemsList(
            rentals,
            { it.name },
            { model.handleRentalChanged(it.id) }
        )
    }

    private fun <T> AutoCompleteTextView.setItemsList(
        items: List<T>,
        transformToText: (T) -> CharSequence,
        handleSelected: (T) -> Unit
    ) {
        val itemTexts = items.map(transformToText)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, itemTexts)
        setAdapter(adapter)
        setOnItemClickListener { _, _, position, _ ->
            handleSelected(items[position])
        }
    }

    private fun setIsLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisibleAndAnimateProgressBar = isLoading
        buttonSubmit.isEnabled = !isLoading
        buttonSubmit.isVisibleAndAnimateProgressBar = !isLoading

        layoutInputResident.isEnabled = !isLoading && model.residentsAvailableData.value == true
        layoutInputRental.isEnabled = !isLoading && model.rentalsAvailableData.value == true
        pickerWorkplacesCount.isEnabled = !isLoading
    }

}