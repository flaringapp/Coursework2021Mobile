package com.flaringapp.coursework2021.presentation.features.dialogs.permission


interface PermissionDialogParent {

    fun onPermissionPositive(tag: String?) {}
    fun onPermissionNegative(tag: String?) {}

}