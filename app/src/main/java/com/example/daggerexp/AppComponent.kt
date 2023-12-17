package com.example.daggerexp

import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import dagger.MembersInjector
import javax.inject.Singleton

@Singleton
@MergeComponent(scope = AppGraph::class)
interface AppComponent : MembersInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }
}
