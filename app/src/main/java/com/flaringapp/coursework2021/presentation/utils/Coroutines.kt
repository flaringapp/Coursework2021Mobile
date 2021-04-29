package com.flaringapp.coursework2021.presentation.utils

import androidx.lifecycle.MutableLiveData
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.withMainContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun CoroutineScope.startLoadingTask(
    loadingData: MutableLiveData<Boolean>,
    task: suspend () -> Unit
): Job {
    loadingData.value = true
    return launchOnIO {
        task()
        withMainContext { loadingData.value = false }
    }
}

fun CoroutineScope.startLoadingTaskInverted(
    loadingData: MutableLiveData<Boolean>,
    task: suspend () -> Unit
): Job {
    return startLoadingTask(
        object : MutableLiveData<Boolean>() {
            override fun setValue(value: Boolean?) {
                loadingData.value = value?.let { !it }
            }
            override fun postValue(value: Boolean?) {
                loadingData.postValue(value?.let { !it })
            }
        },
        task
    )
}