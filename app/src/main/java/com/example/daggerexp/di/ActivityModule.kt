package com.example.daggerexp.di

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCaller
import com.example.daggerexp.ActivityGraph
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(ActivityGraph::class)
object ActivityModule {

    @Provides
    fun provideCaller(activity: ComponentActivity): ActivityResultCaller {
        return activity
    }
}

@Module
@ContributesTo(ActivityGraph::class)
object BillingModule {

    @Provides
    fun provideBillingController(activity: ComponentActivity): BillingController {
        return BillingController {
            "$10 ${activity::class.simpleName}"
        }
    }
}

fun interface BillingController {
    fun getPrice(): String
}