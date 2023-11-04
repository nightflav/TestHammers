package com.example.royaal.testhammers.di

import com.example.royaal.api.MainEntry
import com.example.royaal.presentation.MainEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Singleton
    @Binds
    @RouteKey(MainEntry::class)
    @IntoMap
    fun bindHomeRoute(homeEntryImpl: MainEntryImpl): com.example.royaal.commoncompose.FeatureEntry

}