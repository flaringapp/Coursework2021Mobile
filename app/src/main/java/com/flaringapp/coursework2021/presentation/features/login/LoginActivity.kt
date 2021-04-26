package com.flaringapp.coursework2021.presentation.features.login

import android.app.Activity
import android.content.Intent
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.presentation.base.BaseActivity
import com.flaringapp.coursework2021.presentation.features.main.MainActivity
import com.flaringapp.newsend.presentation.features.login.LoginParent

class LoginActivity : BaseActivity(R.layout.activity_login), LoginParent {

    override fun onLoginSuccessful() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        }
        startActivity(intent)
        setResult(Activity.RESULT_OK)
        finish()
    }
}