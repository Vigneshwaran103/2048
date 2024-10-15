package com.vignesh.atherassignment

import android.app.Application
import com.vignesh.atherassignment.di.repositoryModule
import com.vignesh.atherassignment.di.viewModelModule
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule,repositoryModule)
        }
    }
}