package com.flaringapp.coursework2021.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flaringapp.coursework2021.app.common.takeIfNotEmpty
import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.SafeCallHandler

abstract class BaseViewModel : ViewModel(), BaseViewModelContract, SafeCallHandler {

    override val errorData = MutableLiveData<String>()

    protected suspend fun <T> safeCall(action: suspend () -> CallResult<T>): T? {
        return com.flaringapp.coursework2021.data.common.call.safeCall(this, action)
    }

    override fun <T> handleCallResultError(error: CallResult.Error<T>): Boolean {
        return false
    }

    override fun handleSafeCallError(): Boolean {
        return false
    }

    override fun showError(message: String?) {
        if (message?.takeIfNotEmpty() == null) return
        errorData.value = message
    }

}