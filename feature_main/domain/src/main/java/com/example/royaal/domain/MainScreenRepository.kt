package com.example.royaal.domain

import com.example.royaal.common.model.MenuItem
import com.example.royaal.common.model.PromotionItem
import kotlinx.coroutines.flow.Flow


interface MainScreenRepository {

    fun getPromotions(): Flow<List<PromotionItem>>

    fun getGames(): Flow<MenuItem>

}