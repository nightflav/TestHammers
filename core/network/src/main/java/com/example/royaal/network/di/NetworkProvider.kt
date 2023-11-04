package com.example.royaal.network.di

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.network.GamesApi

interface NetworkProvider {

    val gamesApi: GamesApi

}

val LocalNetworkProvider = compositionLocalOf<NetworkProvider> {
    error("No api provided")
}