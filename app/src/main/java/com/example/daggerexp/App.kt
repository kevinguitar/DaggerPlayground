package com.example.daggerexp

import android.app.Application
import javax.inject.Inject

class App : Application(), HasAnvilAndroidInjector {

    @Inject
    override lateinit var dispatchingAnvilInjector: DispatchingAnvilInjector

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }
}