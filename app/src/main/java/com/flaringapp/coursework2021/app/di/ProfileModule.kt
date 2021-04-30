package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.repository.profile.ProfileCache
import org.koin.dsl.module

val ProfileModule = module {

    factory { ProfileCache.profile }

    factory { ProfileCache.managerInfo }

    factory { ProfileCache.userType }

}