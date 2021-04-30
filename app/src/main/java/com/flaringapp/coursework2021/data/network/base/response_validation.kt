package com.flaringapp.coursework2021.data.network.base

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Response

private val errorResponseAdapter: JsonAdapter<ErrorResponse> by lazy {
    Moshi.Builder()
        .build()
        .adapter(ErrorResponse::class.java)
}

fun <T> Response<List<T>>.validateList(): CallResult<List<T>> {
    return if (isSuccessful) {
        body().let {
            if (it == null) CallResult.Success(emptyList())
            else CallResult.Success(it)
        }
    } else errorBody().parseErrorResult()
}

fun <T> Response<T>.validate(): CallResult<T> {
    return validateAny { CallResult.Success(it!!) }
}

fun ApiResponseSuccess.validateNoData(): CallResultNothing {
    return validateAny { CallResult.Success(it!!) }
}

private fun <T> Response<out T>.validateAny(
    collector: (T?) -> CallResult<T>
): CallResult<T> {
    return if (isSuccessful) body().let(collector)
    else errorBody().parseErrorResult()
}

private fun <T> ValidateableResponse<out T>?.parseResult(
    collector: (T?) -> CallResult<T>
): CallResult<T> {
    if (this == null) return CallResult.UnknownError()
    return if (status == "success") collector(data)
    else CallResult.Error(errorType, message ?: "")
}

private fun <T> ResponseBody?.parseErrorResult(): CallResult<T> {
    val message = this?.source()?.readUtf8() ?: ""
    return CallResult.Error(CallResult.Error.API_ERROR, message)
}

//private fun ResponseBody.convertErrorBody(): ErrorResponseContents? {
//    return try {
//        val source = source()
//        errorResponseAdapter.fromJson(source)?.error
//    } catch (exception: Exception) {
//        null
//    }
//}

private fun <T> ErrorResponseContents?.toCallResult(): CallResult<T> {
    if (this == null) return CallResult.UnknownError()
    return CallResult.Error(errorType, message ?: "")
}