package com.example.royaal.database.model.app

import com.example.royaal.common.model.MenuItem
import com.example.royaal.common.model.MenuType

val GameEntity.detailedGameModel
    get() = MenuItem(
        id = id,
        title = name,
        description = description,
        imgUrl = backgroundUrl,
        type = MenuType.Item
    )

fun MenuItem.toGameEntity(): GameEntity =
    GameEntity(
        id = id,
        name = title,
        backgroundUrl = imgUrl,
        description = description,
    )