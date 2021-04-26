package com.flaringapp.coursework2021.presentation.base

import androidx.lifecycle.LiveData

interface BaseViewModelContract {

    val errorData: LiveData<String>

}