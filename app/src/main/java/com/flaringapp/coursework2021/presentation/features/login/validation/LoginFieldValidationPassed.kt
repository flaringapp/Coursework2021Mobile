package com.flaringapp.coursework2021.presentation.features.login.validation

import com.flaringapp.coursework2021.databinding.FragmentLoginBinding
import com.flaringapp.coursework2021.presentation.features.login.LoginFragment
import com.google.android.material.textfield.TextInputLayout

abstract class LoginFieldValidationPassed(
    private val fieldResolver: (FragmentLoginBinding) -> TextInputLayout
) : LoginFieldValidation {

    override fun apply(fragment: LoginFragment) {
        fieldResolver(fragment.binding).error = null
    }

}
