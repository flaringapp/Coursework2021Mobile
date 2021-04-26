package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.network.RetrofitAdapter
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy { RetrofitAdapter() }

}