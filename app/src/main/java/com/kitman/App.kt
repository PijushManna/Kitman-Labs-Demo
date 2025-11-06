package com.kitman

import android.app.Application
import com.kitman.di.dbModule
import com.kitman.di.networkModule
import com.kitman.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dbModule, networkModule, viewModelModule))
        }
    }
}