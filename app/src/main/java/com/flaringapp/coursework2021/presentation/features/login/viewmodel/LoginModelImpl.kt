package com.flaringapp.coursework2021.presentation.features.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.profile.ProfileRepository
import com.flaringapp.coursework2021.presentation.features.login.state.LoginState
import com.flaringapp.coursework2021.presentation.features.login.state.LoginStateDefault
import com.flaringapp.coursework2021.presentation.features.login.state.LoginStateLoading
import com.flaringapp.coursework2021.presentation.features.login.state.LoginStateSuccessful
import com.flaringapp.coursework2021.presentation.features.login.validation.LoginFieldValidation
import com.flaringapp.coursework2021.presentation.features.login.validation.login.LoginEmpty
import com.flaringapp.coursework2021.presentation.features.login.validation.login.LoginInvalid
import com.flaringapp.coursework2021.presentation.features.login.validation.login.LoginValid
import com.flaringapp.coursework2021.presentation.features.login.validation.password.PasswordEmpty
import com.flaringapp.coursework2021.presentation.features.login.validation.password.PasswordValid

class LoginModelImpl(
    private val repository: ProfileRepository
) : LoginModel() {

    override var login: String = ""
    override var password: String = ""

    override val loginValidation = MutableLiveData<LoginFieldValidation>(LoginValid())
    override val passwordValidation = MutableLiveData<LoginFieldValidation>(PasswordValid())

    override val stateLiveData = MutableLiveData<LoginState>(LoginStateDefault())

    override fun handleLoginChanged(login: String) {
        this.login = login
        loginValidation.value = LoginValid()
    }

    override fun handlePasswordChanged(login: String) {
        this.password = login
        passwordValidation.value = PasswordValid()
    }

    override fun login() {
        if (!validateLoginData()) return

        stateLiveData.value = LoginStateLoading()
        viewModelScope.launchOnIO {
            performLogin()
        }
    }

    private suspend fun performLogin() {
        val isLoggedIn = safeCall {
            repository.login(login, password)
        } != null

        withMainContext {
            stateLiveData.value =
                if (isLoggedIn) LoginStateSuccessful()
                else LoginStateDefault()
        }
    }

    private fun validateLoginData(): Boolean {
        return listOf(
            validateLogin(),
            validatePassword()
        ).all { it }
    }

    private fun validateLogin(): Boolean = when {
        login.isEmpty() -> {
            loginValidation.value = LoginEmpty()
            false
        }
        !Patterns.EMAIL_ADDRESS.matcher(login).matches() -> {
            loginValidation.value = LoginInvalid()
            false
        }
        else -> true
    }

    private fun validatePassword(): Boolean = when {
        password.isEmpty() -> {
            passwordValidation.value = PasswordEmpty()
            false
        }
        else -> true
    }

}