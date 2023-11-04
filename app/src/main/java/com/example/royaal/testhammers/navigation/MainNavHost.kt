package com.example.royaal.testhammers.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.royaal.api.MainEntry
import com.example.royaal.commoncompose.Destinations
import com.example.royaal.commoncompose.find

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destinations: Destinations
) {
    Scaffold {
        NavHost(
            modifier = modifier.padding(it),
            navController = navController,
            startDestination = destinations.find<MainEntry>().destination()
        ) {
            with(destinations.find<MainEntry>()) {
                screen(navController, destinations)
            }
        }
    }
}