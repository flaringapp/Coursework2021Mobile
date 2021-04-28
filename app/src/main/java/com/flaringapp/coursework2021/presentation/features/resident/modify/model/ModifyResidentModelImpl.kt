package com.flaringapp.coursework2021.presentation.features.resident.modify.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour.ModifyResidentBehaviour
import com.flaringapp.coursework2021.presentation.features.resident.modify.models.ResidentEditableData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.startLoadingTask

class ModifyResidentModelImpl : ModifyResidentModel() {

    override val nameData = SingleLiveEvent<CharSequence>()
    override val surnameData = SingleLiveEvent<CharSequence>()
    override val emailData = SingleLiveEvent<CharSequence>()
    override val descriptionData = SingleLiveEvent<CharSequence>()

    override val nameErrorData = SingleLiveEvent<Int?>()
    override val surnameErrorData = SingleLiveEvent<Int?>()
    override val emailErrorData = SingleLiveEvent<Int?>()

    override val loadingData = MutableLiveData(false)

    override val closeScreenData = SingleLiveEvent<Unit>()

    private var buildingId: String? = null

    private lateinit var behaviour: ModifyResidentBehaviour

    private var sourceResident: Resident? = null
    private lateinit var editor: ResidentEditableData

    override fun init(buildingId: String?, behaviour: ModifyResidentBehaviour) {
        this.buildingId = buildingId
        this.behaviour = behaviour
        sourceResident = behaviour.resident
        editor = behaviour.preliminaryData ?: ResidentEditableData()
        editor.setup()
    }

    override fun handleNameChanged(name: String) {
        editor.name = name
        nameErrorData.value = null
    }

    override fun handleSurnameChanged(surname: String) {
        editor.surname = surname
        surnameErrorData.value = null
    }

    override fun handleEmailChanged(email: String) {
        editor.email = email
        emailErrorData.value = null
    }

    override fun handleDescriptionChanged(description: String) {
        editor.description = description
    }

    override fun saveResident() {
        if (!isValid()) return
        performSaveResident()
    }

    private fun ResidentEditableData.setup() {
        nameData.value = name
        surnameData.value = surname
        emailData.value = email
        descriptionData.value = description
    }

    private fun performSaveResident() {
        viewModelScope.startLoadingTask(loadingData) {
            val resident = editor.toResident(sourceResident, buildingId)
            safeCall { behaviour.modifyResident(resident) }
                ?: return@startLoadingTask

            withMainContext {
                closeScreenData.value = Unit
            }
        }
    }

    private fun isValid(): Boolean {
        when {
            editor.name.trim().isEmpty() -> {
                nameErrorData.value = R.string.error_resident_empty_name
            }
            editor.surname.trim().isEmpty() -> {
                surnameErrorData.value = R.string.error_resident_empty_surname
            }
            editor.email.trim().isEmpty() -> {
                emailErrorData.value = R.string.error_resident_empty_email
            }
            else -> return true
        }
        return false
    }
}