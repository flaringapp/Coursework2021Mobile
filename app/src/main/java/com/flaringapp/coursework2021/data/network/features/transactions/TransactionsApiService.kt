package com.flaringapp.coursework2021.data.network.features.transactions

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.features.transactions.request.AddTransactionRequest
import com.flaringapp.coursework2021.data.network.features.transactions.response.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface TransactionsApiService {

    @GET("transactions")
    suspend fun getTransactions(
        @Query("manager_id") managerId: String? = null
    ): ApiResponseList<TransactionResponse>

    @PUT("transaction")
    suspend fun addTransaction(
        @Body request: AddTransactionRequest
    ): ApiResponse<TransactionResponse>

}