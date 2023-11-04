package com.example.royaal.testhammers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.royaal.api.LocalMainRepositoryProvider
import com.example.royaal.database.model.di.LocalDatabaseProvider
import com.example.royaal.network.di.LocalNetworkProvider
import com.example.royaal.testhammers.navigation.MainNavHost
import com.example.royaal.testhammers.ui.theme.TestHammersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (this.application as BaseApp).appComponent
        val destinations = appComponent.destinations
        setContent {
            CompositionLocalProvider(
                LocalNetworkProvider provides appComponent,
                LocalDatabaseProvider provides appComponent,
                LocalMainRepositoryProvider provides appComponent
            ) {
                val navController = rememberNavController()
                TestHammersTheme {
                    MainNavHost(
                        navController = navController,
                        destinations = destinations
                    )
                }
            }
        }
    }
}
