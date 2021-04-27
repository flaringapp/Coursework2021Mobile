package com.flaringapp.coursework2021.presentation.features.dialogs.permission.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PermissionOnCancelAction : Parcelable {
    @Parcelize
    object Positive : PermissionOnCancelAction()
    @Parcelize
    object Negative : PermissionOnCancelAction()
    @Parcelize
    object None : PermissionOnCancelAction()
}