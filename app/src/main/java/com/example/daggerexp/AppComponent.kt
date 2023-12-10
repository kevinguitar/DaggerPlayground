package com.example.daggerexp

import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationComponent
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@SingleIn(ApplicationScope::class)
@MergeComponent(
    scope = ApplicationScope::class,
    modules = [AndroidInjectionModule::class]
)
interface AppComponent : ApplicationComponent, AppServiceProvider {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }
}

// Weird stuff, extending AndroidInjector directly to AppComponent fail
// the Whetstone code generation somehow, looks like a bug.
interface AppServiceProvider : AndroidInjector<App>