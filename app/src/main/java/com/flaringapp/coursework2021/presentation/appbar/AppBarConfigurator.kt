package com.flaringapp.coursework2021.presentation.appbar

interface AppBarConfigurator {

    fun configureAppBar(configure: AppBarConfigurationChange.() -> Unit)

}