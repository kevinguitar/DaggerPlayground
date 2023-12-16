package com.example.daggerexp.di

import com.example.daggerexp.AppGraph
import com.example.daggerexp.MainActivity2
import com.example.daggerexp.MainActivity2DepModule
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.MembersInjector
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Inject

@Module(subcomponents = [MainActivity2Subcomponent::class])
@ContributesTo(AppGraph::class)
interface MainActivity2InjectorBinder {

    @IntoMap
    @Binds
    @ClassKey(MainActivity2::class)
    fun bindMainActivity2AnvilInjector(
        impl: MainActivity2Subcomponent.Factory
    ): AnvilInjector.Factory<*>
}

@Subcomponent(modules = [MainActivity2DepModule::class])
interface MainActivity2Subcomponent : AnvilInjector<MainActivity2> {
    @Subcomponent.Factory
    interface Factory : AnvilInjector.Factory<MainActivity2>
}