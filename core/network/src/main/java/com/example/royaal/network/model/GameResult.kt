package com.example.royaal.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResult(
    @SerialName("background_image")
    val backgroundImage: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
)