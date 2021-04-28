package com.flaringapp.coursework2021.app.di

import com.flaringapp.coursework2021.data.repository.entity.EntityRepository
import com.flaringapp.coursework2021.data.repository.entity.EntityRepositoryImpl
import com.flaringapp.coursework2021.data.repository.entity.storage.BuildingsStorage
import com.flaringapp.coursework2021.data.repository.entity.storage.RoomsStorage
import com.flaringapp.coursework2021.data.repository.profile.ProfileRepository
import com.flaringapp.coursework2021.data.repository.profile.ProfileRepositoryImpl
import org.koin.dsl.binds
import org.koin.dsl.module

val RepositoryModule = module {

    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }

    single<EntityRepository> { EntityRepositoryImpl(get() , get()) } binds arrayOf(
        BuildingsStorage::class,
        RoomsStorage::class
    )

}