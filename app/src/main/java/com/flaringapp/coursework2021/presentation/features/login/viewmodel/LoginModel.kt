package com.flaringapp.coursework2021.presentation.features.login.viewmodel

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.login.state.LoginState
import com.flaringapp.coursework2021.presentation.features.login.validation.LoginFieldValidation

abstract class LoginModel: BaseViewModel() {

    abstract val login: String
    abstract val password: String

    abstract val loginValidation: LiveData<LoginFieldValidation>
    abstract val passwordValidation: LiveData<LoginFieldValidation>

    abstract val stateLiveData: LiveData<LoginState>

    abstract fun handleLoginChanged(login: String)
    abstract fun handlePasswordChanged(login: String)

    abstract fun login()

}