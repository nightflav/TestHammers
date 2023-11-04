package com.example.royaal.data.di

import com.example.royaal.data.MainScreenRepositoryImpl
import com.example.royaal.domain.MainScreenRepository
import dagger.Binds
import dagger.Module

@Module
interface MainScreenRepoModule {

    @Binds
    fun bindMainScreenRepo(impl: MainScreenRepositoryImpl): MainScreenRepository
}