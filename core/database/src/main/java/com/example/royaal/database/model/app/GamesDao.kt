package com.example.royaal.database.model.app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.royaal.database.model.app.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGame(game: GameEntity)

    @Query("delete from AppDBTable where id=:id")
    suspend fun deleteGameById(id: Int)

    @Query("select * from AppDBTable")
    suspend fun getGames(): List<GameEntity>

    @Query("delete from AppDBTable")
    suspend fun clearTable()

}