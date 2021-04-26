package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.repository.profile.ProfileRepository
import com.flaringapp.coursework2021.data.repository.profile.ProfileRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }

}