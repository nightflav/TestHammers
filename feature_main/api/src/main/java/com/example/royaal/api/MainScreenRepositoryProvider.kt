package com.example.royaal.api

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.domain.MainScreenRepository

interface MainScreenRepositoryProvider {

    val gamesRepo: MainScreenRepository
}

val LocalMainRepositoryProvider = compositionLocalOf<MainScreenRepositoryProvider> {
    error("No MainScreenRepository provided")
}