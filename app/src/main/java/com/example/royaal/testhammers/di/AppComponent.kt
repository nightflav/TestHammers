package com.example.royaal.testhammers.di

import android.app.Application
import com.example.royaal.api.MainScreenRepositoryProvider
import com.example.royaal.commoncompose.Destinations
import com.example.royaal.data.di.MainScreenRepoModule
import com.example.royaal.database.model.di.DatabaseModule
import com.example.royaal.database.model.di.DatabaseProvider
import com.example.royaal.network.di.NetworkModule
import com.example.royaal.network.di.NetworkProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        NavigationModule::class,
        MainScreenRepoModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent :
    MainScreenRepositoryProvider,
    NetworkProvider,
    DatabaseProvider {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            application: Application,
        ): AppComponent
    }

    val destinations: Destinations

}