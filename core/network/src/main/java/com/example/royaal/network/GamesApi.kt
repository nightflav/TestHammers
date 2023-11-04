package com.example.royaal.network

import com.example.royaal.network.model.GameDetailsResponse
import com.example.royaal.network.model.GameNetworkResponse
import com.example.royaal.network.model.StoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi {

    @GET("games")
    suspend fun getGames(): Response<GameNetworkResponse>

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int,
    ): Response<GameDetailsResponse>

    @GET("stores")
    suspend fun getStores(): Response<StoreResponse>

}