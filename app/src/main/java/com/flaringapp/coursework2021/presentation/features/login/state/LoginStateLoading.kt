package com.flaringapp.coursework2021.presentation.features.login.state

import androidx.core.view.isInvisible
import com.flaringapp.coursework2021.presentation.features.login.LoginFragment

class LoginStateLoading: LoginState {

    override fun apply(fragment: LoginFragment) {
        fragment.binding.progressbar.isInvisible = false
    }

    override fun release(fragment: LoginFragment) {
        fragment.binding.progressbar.isInvisible = true
    }

}