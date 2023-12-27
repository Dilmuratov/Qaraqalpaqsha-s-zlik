package com.example.qaraqalpaqshaszlik

import android.app.Application
import com.example.qaraqalpaqshaszlik.di.appModule
import com.example.qaraqalpaqshaszlik.di.dataModule
import com.example.qaraqalpaqshaszlik.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, dataModule, domainModule))
            androidContext(this@App)
        }
    }
}