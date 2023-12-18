package com.example.daggerexp

import android.app.Fragment
import android.app.Service
import android.view.View
import androidx.activity.ComponentActivity
import com.squareup.anvil.annotations.ContributesTo
import dagger.BindsInstance
import dagger.MembersInjector
import dagger.Module
import dagger.multibindings.Multibinds

interface AnvilAndroidInjector<T> {
    val injector: MembersInjector<T>

    fun inject(target: T) {
        injector.injectMembers(target)
    }

    interface Factory<T> {
        fun create(@BindsInstance instance: T): AnvilAndroidInjector<T>
    }
}

object AnvilAndroidInjection {

    fun <T : ComponentActivity> inject(activity: T) {
        val injector = (activity.applicationContext as HasAnvilAndroidInjector)
            .dispatchingAnvilInjector[activity::class.java]

        @Suppress("UNCHECKED_CAST")
        (injector as AnvilAndroidInjector.Factory<ComponentActivity>)
            .create(activity)
            .inject(activity)
    }

    fun inject(fragment: Fragment) {

    }

    fun inject(view: View) {

    }

    fun inject(service: Service) {

    }

    // Separate class
    // fun inject(worker: ListenableWorker)
}

@Module
@ContributesTo(AppGraph::class)
interface DispatchingAnvilInjectorModule {

    @Multibinds
    fun dispatchingAnvilInjector(): DispatchingAnvilInjector
}

interface HasAnvilAndroidInjector {
    val dispatchingAnvilInjector: DispatchingAnvilInjector
}

typealias DispatchingAnvilInjector = Map<@JvmSuppressWildcards Class<*>, @JvmSuppressWildcards AnvilAndroidInjector.Factory<*>>