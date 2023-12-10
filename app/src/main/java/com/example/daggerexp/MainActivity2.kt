package com.example.daggerexp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.deliveryhero.whetstone.app.ApplicationScope
import com.example.daggerexp.ui.theme.DaggerExpTheme
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

@Module
@ContributesTo(ApplicationScope::class)
interface MainActivity2Module {

    @ContributesAndroidInjector(modules = [MainActivity2DepModule::class])
    fun mainActivity(): MainActivity2
}

@Module
object MainActivity2DepModule {

    @Provides
    fun provideParam(activity: MainActivity2): String {
        return "MainActivity2"
    }
}

class MainActivity2 : ComponentActivity() {

    @Inject lateinit var param: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            DaggerExpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(param)
                }
            }
        }
    }
}