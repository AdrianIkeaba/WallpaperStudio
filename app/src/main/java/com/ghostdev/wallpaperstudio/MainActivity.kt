package com.ghostdev.wallpaperstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.ghostdev.wallpaperstudio.di.repositoryModule
import com.ghostdev.wallpaperstudio.navigation.AppNavHost
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin {
            androidContext(this@MainActivity)
            modules(
                repositoryModule
            )
        }
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                AppNavHost()
            }
        }
    }
}