package com.example.daggerexp

import androidx.activity.ComponentActivity
import com.squareup.anvil.annotations.ContributesTo
import dagger.BindsInstance
import dagger.MembersInjector
import dagger.Module
import dagger.multibindings.Multibinds

interface AnvilInjector<T> {
    val injector: MembersInjector<T>

    fun inject(target: T) {
        injector.injectMembers(target)
    }

    interface Factory<T> {
        fun create(@BindsInstance instance: T): AnvilInjector<T>
    }
}

object AnvilInjection {

    fun <T : ComponentActivity> inject(activity: T) {
        val injector = (activity.applicationContext as HasAnvilInjector)
            .dispatchingAnvilInjector[activity::class.java]

        @Suppress("UNCHECKED_CAST")
        (injector as AnvilInjector.Factory<ComponentActivity>)
            .create(activity)
            .inject(activity)
    }
}

@Module
@ContributesTo(AppGraph::class)
interface DispatchingAnvilInjectorModule {

    @Multibinds
    fun dispatchingAnvilInjector(): DispatchingAnvilInjector
}

interface HasAnvilInjector {
    val dispatchingAnvilInjector: DispatchingAnvilInjector
}

typealias DispatchingAnvilInjector = Map<@JvmSuppressWildcards Class<*>, @JvmSuppressWildcards AnvilInjector.Factory<*>>