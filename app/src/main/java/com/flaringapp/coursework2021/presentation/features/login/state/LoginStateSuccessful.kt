package com.flaringapp.coursework2021.presentation.features.login.state

import com.flaringapp.coursework2021.presentation.features.login.LoginFragment
import com.flaringapp.newsend.presentation.features.login.LoginParent

class LoginStateSuccessful: LoginState {

    override fun apply(fragment: LoginFragment) {
        (fragment.activity as LoginParent).onLoginSuccessful()
    }

    override fun release(fragment: LoginFragment) {

    }
}
