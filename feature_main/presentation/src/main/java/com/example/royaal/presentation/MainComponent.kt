package com.example.royaal.presentation

import com.example.royaal.api.MainScreenRepositoryProvider
import com.example.royaal.common.PerFeature
import com.example.royaal.database.model.di.DatabaseProvider
import com.example.royaal.network.di.NetworkProvider
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        NetworkProvider::class,
        DatabaseProvider::class,
        MainScreenRepositoryProvider::class
    ]
)
internal interface MainComponent {

    @PerFeature
    val mainViewModel: MainViewModel

    @Component.Factory
    interface Factory {
        fun create(
            networkProvider: NetworkProvider,
            databaseProvider: DatabaseProvider,
            mainScreenRepositoryProvider: MainScreenRepositoryProvider
        ): MainComponent
    }
}