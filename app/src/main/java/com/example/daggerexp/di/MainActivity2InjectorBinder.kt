package com.example.daggerexp.di

import com.example.daggerexp.AppGraph
import com.example.daggerexp.MainActivity2
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.MembersInjector
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Inject

class MainActivity2AnvilInjector @Inject constructor(
    override val injector: MembersInjector<MainActivity2>
) : AnvilInjector<MainActivity2>

@Module
@ContributesTo(AppGraph::class)
interface MainActivity2InjectorBinder {

    @IntoMap
    @Binds
    @ClassKey(MainActivity2::class)
    fun bindMainActivity2AnvilInjector(impl: MainActivity2AnvilInjector): AnvilInjector<*>
}