package com.flaringapp.coursework2021.app

import android.app.Application
import com.flaringapp.coursework2021.app.di.DataModule
import com.flaringapp.coursework2021.app.di.NetworkModule
import com.flaringapp.coursework2021.app.di.PresentationModule
import com.flaringapp.coursework2021.app.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationImpl: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ApplicationImpl)
            modules(NetworkModule, DataModule, RepositoryModule, PresentationModule)
        }
    }

}