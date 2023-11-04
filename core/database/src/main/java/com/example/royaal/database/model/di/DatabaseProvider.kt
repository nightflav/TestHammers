package com.example.royaal.database.model.di

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.database.model.app.GamesDao

interface DatabaseProvider {

    val gamesDao: GamesDao

}

val LocalDatabaseProvider = compositionLocalOf<DatabaseProvider> {
    error("No database provided")
}