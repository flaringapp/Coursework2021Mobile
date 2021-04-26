package com.flaringapp.coursework2021.presentation.features.login.validation.password

import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.presentation.features.login.validation.LoginFieldValidationFailed

class PasswordEmpty : LoginFieldValidationFailed(
    { it.layoutInputPassword },
    R.string.error_password_empty
)
