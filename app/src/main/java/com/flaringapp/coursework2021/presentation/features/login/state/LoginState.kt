package com.flaringapp.coursework2021.presentation.features.login.state

import com.flaringapp.coursework2021.presentation.features.login.LoginFragment

interface LoginState {

    fun apply(fragment: LoginFragment)
    fun release(fragment: LoginFragment)

}