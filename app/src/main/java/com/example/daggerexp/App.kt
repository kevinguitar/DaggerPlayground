package com.example.daggerexp

import android.app.Application
import androidx.activity.ComponentActivity
import com.example.daggerexp.di.AnvilInjector
import com.example.daggerexp.di.HasAnvilInjectors
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector, HasAnvilInjectors {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var anvilInjectors: Map<@JvmSuppressWildcards Class<*>, @JvmSuppressWildcards AnvilInjector.Factory<*>>

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}