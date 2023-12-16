package com.example.daggerexp.di

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCaller
import com.example.daggerexp.ActivityGraph
import com.example.daggerexp.AppGraph
import com.example.daggerexp.MainActivity2
import com.example.daggerexp.MainActivity2DepModule
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

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

@MergeSubcomponent(
    scope = ActivityGraph::class,
    modules = [SubcomponentDefaultModule::class, MainActivity2DepModule::class]
)
interface MainActivity2Subcomponent : AnvilInjector<MainActivity2> {

    @Subcomponent.Factory
    interface Factory : AnvilInjector.Factory<MainActivity2>
}

@Module
object SubcomponentDefaultModule {

    @Provides
    fun provideComponentActivity(activity: MainActivity2): ComponentActivity {
        return activity
    }
}