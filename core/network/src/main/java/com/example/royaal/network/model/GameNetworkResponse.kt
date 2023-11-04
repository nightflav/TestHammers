package com.example.royaal.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameNetworkResponse(
    @SerialName("results")
    val results: List<GameResult>
)