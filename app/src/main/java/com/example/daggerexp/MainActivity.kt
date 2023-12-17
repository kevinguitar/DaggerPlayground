package com.example.daggerexp

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
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
object MainActivityDepModule {

    @Provides
    fun provideParam(activity: MainActivity): String {
        return "I'm MainActivity"
    }
}

@ContributesActivityInjector(AppGraph::class, modules = [MainActivityDepModule::class])
class MainActivity : ComponentActivity() {

    @Inject lateinit var param: String
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