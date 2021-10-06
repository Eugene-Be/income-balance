package com.app.incomebalance.common

import android.app.Application
import android.content.Context
import com.app.incomebalance.di.AppComponent
import com.app.incomebalance.di.AppModule
import com.app.incomebalance.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }