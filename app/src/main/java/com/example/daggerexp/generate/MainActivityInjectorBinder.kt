package com.example.daggerexp.generate

import com.example.daggerexp.ActivityGraph
import com.example.daggerexp.AnvilInjector
import com.example.daggerexp.AppGraph
import com.example.daggerexp.MainActivity
import com.example.daggerexp.MainActivityDepModule
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivitySubcomponent::class])
@ContributesTo(AppGraph::class)
interface MainActivityInjectorBinder {

    @IntoMap
    @Binds
    @ClassKey(MainActivity::class)
    fun bindMainActivityAnvilInjector(
        impl: MainActivitySubcomponent.Factory
    ): AnvilInjector.Factory<*>
}

@MergeSubcomponent(
    scope = ActivityGraph::class,
    modules = [SubcomponentDefaultModule::class, MainActivityDepModule::class]
)
interface MainActivitySubcomponent : AnvilInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory : AnvilInjector.Factory<MainActivity>
}
