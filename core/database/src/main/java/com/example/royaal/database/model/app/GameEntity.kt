package com.example.royaal.database.model.app

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.royaal.core.database.Converters

@Entity(
    tableName = "AppDBTable"
)
@TypeConverters(Converters::class)
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val backgroundUrl: String,
    val description: String,
)
