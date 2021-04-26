package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.storage.DataStorage
import com.flaringapp.coursework2021.data.storage.DataStorageImpl
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.data.text.TextProviderImpl
import org.koin.dsl.module

val DataModule = module {

    single<TextProvider> { TextProviderImpl(get()) }

    single<DataStorage> { DataStorageImpl(get()) }

}