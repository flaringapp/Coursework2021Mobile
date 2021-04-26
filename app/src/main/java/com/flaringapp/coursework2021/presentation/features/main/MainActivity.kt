package com.flaringapp.coursework2021.presentation.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.presentation.features.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        startActivity(Intent(this, LoginActivity::class.java))
    }
}