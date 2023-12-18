package com.example.daggerexp.generate

import androidx.activity.ComponentActivity
import com.example.daggerexp.ActivityModule
import com.example.daggerexp.ActivityScope
import com.example.daggerexp.AnvilAndroidInjector
import com.example.daggerexp.AppGraph
import com.example.daggerexp.MainActivity
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Binds
import dagger.Module
import dagger.Provides
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
    ): AnvilAndroidInjector.Factory<*>
}

@MergeSubcomponent(
    scope = MainActivity::class,
    modules = [MainActivityDefaultModule::class, ActivityModule::class]
)
@ActivityScope
interface MainActivitySubcomponent : AnvilAndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory : AnvilAndroidInjector.Factory<MainActivity>
}

@Module
object MainActivityDefaultModule {
    @Provides
    fun provideComponentActivity(activity: MainActivity): ComponentActivity {
        return activity
    }
}