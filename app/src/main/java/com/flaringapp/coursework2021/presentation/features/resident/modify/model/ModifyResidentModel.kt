package com.flaringapp.coursework2021.presentation.features.resident.modify.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour.ModifyResidentBehaviour

abstract class ModifyResidentModel : BaseViewModel() {

    abstract val nameData: LiveData<CharSequence>
    abstract val surnameData: LiveData<CharSequence>
    abstract val emailData: LiveData<CharSequence>
    abstract val descriptionData: LiveData<CharSequence>

    abstract val nameErrorData: LiveData<Int?>
    abstract val surnameErrorData: LiveData<Int?>
    abstract val emailErrorData: LiveData<Int?>

    abstract val loadingData: LiveData<Boolean>

    abstract val closeScreenData: LiveData<Unit>

    abstract fun init(buildingId: String?, behaviour: ModifyResidentBehaviour)

    abstract fun handleNameChanged(name: String)
    abstract fun handleSurnameChanged(surname: String)
    abstract fun handleEmailChanged(email: String)
    abstract fun handleDescriptionChanged(description: String)

    abstract fun saveResident()

}