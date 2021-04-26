package com.flaringapp.coursework2021.data.common.call

interface SafeCallHandler {

    fun <T> handleCallResultError(error: CallResult.Error<T>): Boolean

    fun handleSafeCallError(): Boolean

    fun showError(message: String?)

}