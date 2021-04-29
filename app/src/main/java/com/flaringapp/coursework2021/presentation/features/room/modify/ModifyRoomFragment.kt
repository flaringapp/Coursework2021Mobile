package com.flaringapp.coursework2021.presentation.features.room.modify

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.data.repository.entity.models.RoomType
import com.flaringapp.coursework2021.databinding.FragmentRoomModifyBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.room.modify.model.ModifyRoomModel
import com.flaringapp.coursework2021.presentation.utils.common.isVisibleAndAnimateProgressBar
import com.flaringapp.coursework2021.presentation.utils.doOnDoneClicked
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModifyRoomFragment : ModelledFragment(R.layout.fragment_room_modify) {

    override val model: ModifyRoomModel by viewModel()

    private val args by navArgs<ModifyRoomFragmentArgs>()

    private val binding: FragmentRoomModifyBinding by viewBinding(FragmentRoomModifyBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.init(args.params.buildingId, args.params.behaviour)
    }

    override fun initViews() = with(binding) {
        inputName.doAfterTextChanged {
            model.handleNameChanged(it.toString())
        }
        inputPrice.doAfterTextChanged {
            model.handlePriceChanged(it.toString())
        }
        inputDescription.doAfterTextChanged {
            model.handleDescriptionChanged(it.toString())
        }
        inputArea.doAfterTextChanged {
            model.handleAreaChanged(it.toString())
        }
        buttonRoomTypePrivate.setOnClickListener {
            model.handleRoomTypeSelected(RoomType.Private)
        }
        buttonRoomTypeOpenSpace.setOnClickListener {
            model.handleRoomTypeSelected(RoomType.OpenSpace)
        }
        checkBoxHasBoard.setOnCheckedChangeListener { _, isChecked ->
            model.handleHasBoardChanged(isChecked)
        }
        checkBoxHasBalcony.setOnCheckedChangeListener { _, isChecked ->
            model.handleHasBalconyChanged(isChecked)
        }
        pickerWorkplacesCount.onAmountChange = {
            model.handleWorkplacesCountChanged(it)
        }
        pickerWindowCount.onAmountChange = {
            model.handleWindowCountChanged(it)
        }
        inputArea.doOnDoneClicked {
            buttonSubmit.performClick()
        }
        buttonSubmit.setOnClickListener {
            model.saveRoom()
        }
    }

    override fun observeModel() = with(model) {
        nameData.observe(viewLifecycleOwner) { name ->
            binding.inputName.setText(name)
        }
        priceData.observe(viewLifecycleOwner) { price ->
            binding.inputPrice.setText(price)
        }
        descriptionData.observe(viewLifecycleOwner) { description ->
            binding.inputDescription.setText(description)
        }
        roomTypeData.observe(viewLifecycleOwner) { roomType ->
            setRoomType(roomType)
        }
        hasBoardData.observe(viewLifecycleOwner) { hasBoard ->
            binding.checkBoxHasBoard.isChecked = hasBoard
        }
        hasBalconyData.observe(viewLifecycleOwner) { hasBalcony ->
            binding.checkBoxHasBalcony.isChecked = hasBalcony
        }
        workplacesCountData.observe(viewLifecycleOwner) { workplaces ->
            binding.pickerWorkplacesCount.setSelectedAmount(workplaces)
        }
        windowCountData.observe(viewLifecycleOwner) { windowCount ->
            binding.pickerWindowCount.setSelectedAmount(windowCount)
        }
        areaData.observe(viewLifecycleOwner) { area ->
            binding.inputArea.setText(area)
        }

        nameErrorData.observe(viewLifecycleOwner) { nameError ->
            binding.layoutInputName.error = nameError?.let { getString(it) }
        }
        priceErrorData.observe(viewLifecycleOwner) { priceError ->
            binding.layoutInputPrice.error = priceError?.let { getString(it) }
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

    private fun setRoomType(roomType: RoomType) {
        val button = when(roomType) {
            RoomType.Private -> binding.buttonRoomTypePrivate
            RoomType.OpenSpace -> binding.buttonRoomTypeOpenSpace
        }
        listOf(binding.buttonRoomTypePrivate, binding.buttonRoomTypeOpenSpace).forEach {
            it.isSelected = it == button
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        binding.progressBar.isVisibleAndAnimateProgressBar = isLoading

        binding.buttonSubmit.isEnabled = !isLoading
        binding.buttonSubmit.isVisibleAndAnimateProgressBar = !isLoading

        listOf(
            binding.layoutInputName,
            binding.layoutInputPrice,
            binding.layoutInputDescription,
            binding.buttonRoomTypePrivate,
            binding.buttonRoomTypeOpenSpace,
            binding.checkBoxHasBoard,
            binding.checkBoxHasBalcony,
            binding.pickerWorkplacesCount,
            binding.pickerWindowCount,
            binding.layoutInputArea,
        ).forEach { it.isEnabled = !isLoading }
    }

}