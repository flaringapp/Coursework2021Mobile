package com.flaringapp.coursework2021.data.network.base

interface ValidateableResponse<T> {
    val status: String
    val message: String?
    val data: T?
    val errorType: String?
}