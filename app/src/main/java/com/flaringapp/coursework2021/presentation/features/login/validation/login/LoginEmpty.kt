package com.flaringapp.coursework2021.presentation.features.login.validation.login

import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.presentation.features.login.validation.LoginFieldValidationFailed

class LoginEmpty : LoginFieldValidationFailed(
    { it.layoutInputLogin },
    R.string.error_login_empty
)