package com.flaringapp.coursework2021.data.repository.transactions.models

import com.flaringapp.coursework2021.data.text.TextProvider

class TransactionResident(
    val residentId: String,
    val residentName: String,
    val residentSurname: String
) {

    fun formatNameSurname(textProvider: TextProvider): CharSequence {
        return textProvider.formatNameSurname(residentName, residentSurname)
    }

}