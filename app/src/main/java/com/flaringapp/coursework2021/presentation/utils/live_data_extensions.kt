package com.flaringapp.coursework2021.presentation.utils

import androidx.lifecycle.MutableLiveData

var <T> MutableLiveData<T>.valueIfHasObservers: T?
    get() = value
    set(value) {
        if (!hasActiveObservers()) return
         this.value = value
    }