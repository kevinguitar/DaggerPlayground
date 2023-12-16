package com.example.daggerexp.di

import androidx.activity.ComponentActivity
import com.example.daggerexp.AppGraph
import com.squareup.anvil.annotations.ContributesTo
import dagger.MembersInjector
import dagger.Module
import dagger.multibindings.Multibinds

interface AnvilInjector<T> {
    val injector: MembersInjector<T>

    fun inject(target: T) {
        injector.injectMembers(target)
    }
}

object AnvilInjection {

    inline fun <reified T : ComponentActivity> inject(activity: T) {
        val injector = (activity.applicationContext as HasAnvilInjectors)
            .anvilInjectors[activity::class.java]

        @Suppress("UNCHECKED_CAST")
        (injector as AnvilInjector<ComponentActivity>).inject(activity)
    }
}

@Module
@ContributesTo(AppGraph::class)
interface AnvilInjectorsDependency {

    @Multibinds
    fun activityInjectors(): Map<Class<*>, AnvilInjector<*>>
}

interface HasAnvilInjectors {
    val anvilInjectors: Map<Class<*>, AnvilInjector<*>>
}