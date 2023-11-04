package com.example.royaal.presentation

import com.example.royaal.common.model.MenuItem
import com.example.royaal.common.model.PromotionItem

data class MainScreenState(
    val games: List<MenuItem> = emptyList(),
    val promotions: List<PromotionItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: Throwable? = null
)