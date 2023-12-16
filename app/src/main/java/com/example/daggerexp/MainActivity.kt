package com.example.daggerexp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.daggerexp.ui.theme.DaggerExpTheme
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

@Module
@ContributesTo(AppGraph::class)
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityDepModule::class])
    fun mainActivity(): MainActivity
}

@Module
object MainActivityDepModule {

    @Provides
    fun provideComponentActivity(activity: Activity): ComponentActivity {
        return activity as ComponentActivity
    }

    @Provides
    fun provideParam(activity: ComponentActivity): String {
        return "Activity Param"
    }
}

class MainActivity : ComponentActivity() {

//    @Inject lateinit var param: String

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
//                    Greeting(param)
                    Greeting(name = "asdf")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaggerExpTheme {
        Greeting("Android")
    }
}