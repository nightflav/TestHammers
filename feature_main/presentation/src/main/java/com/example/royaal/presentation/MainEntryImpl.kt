package com.example.royaal.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.royaal.api.LocalMainRepositoryProvider
import com.example.royaal.api.MainEntry
import com.example.royaal.commoncompose.Destinations
import com.example.royaal.commoncompose.daggerViewModel
import com.example.royaal.database.model.di.LocalDatabaseProvider
import com.example.royaal.network.di.LocalNetworkProvider
import com.example.royaal.presentation.ui.MainScreen
import javax.inject.Inject

class MainEntryImpl @Inject constructor() : MainEntry() {

    override val selectedIcon: ImageVector
        get() = Icons.Filled.Home
    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.Home
    override val name: String
        get() = "Home"

    @Composable
    override fun Screen(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val networkProvider = LocalNetworkProvider.current
        val databaseProvider = LocalDatabaseProvider.current
        val mainScreenRepositoryProvider = LocalMainRepositoryProvider.current
        val mainComponent = DaggerMainComponent.factory().create(
            networkProvider, databaseProvider, mainScreenRepositoryProvider
        )
        val viewModel = daggerViewModel {
            mainComponent.mainViewModel
        }
        MainScreen(
            viewModel = viewModel
        )
    }

}