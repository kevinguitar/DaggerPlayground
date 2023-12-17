package com.example.daggerexp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCaller
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.daggerexp.ui.theme.DaggerExpTheme
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
object MainActivity2DepModule {

    @Provides
    fun provideParam(activity: MainActivity2): String {
        return activity::class.simpleName!!
    }
}

@ContributesActivityInjector(AppGraph::class, modules = [MainActivity2DepModule::class])
class MainActivity2 : ComponentActivity() {

    @Inject lateinit var param: String
    @Inject lateinit var caller: ActivityResultCaller
    @Inject lateinit var billingController: BillingController

    override fun onCreate(savedInstanceState: Bundle?) {
        AnvilInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            DaggerExpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(billingController.getPrice())
                    caller.toString()
                }
            }
        }
    }
}