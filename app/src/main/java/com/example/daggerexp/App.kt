package com.example.daggerexp

import android.app.Application
import javax.inject.Inject

class App : Application(), HasAnvilInjectors {

    @Inject
    override lateinit var anvilInjectors: Map<@JvmSuppressWildcards Class<*>, @JvmSuppressWildcards AnvilInjector.Factory<*>>

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.injectMembers(this)
    }
}