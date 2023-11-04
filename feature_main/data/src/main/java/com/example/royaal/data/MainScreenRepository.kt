package com.example.royaal.data

import com.example.royaal.common.model.MenuItem
import com.example.royaal.common.model.MenuType
import com.example.royaal.common.model.PromotionItem
import com.example.royaal.database.model.app.GamesDao
import com.example.royaal.database.model.app.toGameEntity
import com.example.royaal.domain.MainScreenRepository
import com.example.royaal.network.GamesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val gamesApi: GamesApi,
    private val gamesDao: GamesDao
) : MainScreenRepository {

    override fun getPromotions(): Flow<List<PromotionItem>> = flow {
        val response = gamesApi.getStores()
        if (response.isSuccessful) {
            val body = response.body()!!.results
            emit(body.map {
                PromotionItem(
                    id = it.id,
                    imgUrl = it.imageBackground
                )
            })
        } else {
            emit(emptyList())
        }
    }.catch {
        emit(emptyList())
    }

    override fun getGames(): Flow<MenuItem> = flow {
        val response = gamesApi.getGames()
        if (response.isSuccessful) {
            val gamesIds = response.body()!!.results.map {
                it.id
            }
            for (id in gamesIds) {
                val gameDetailsResponse = gamesApi.getGameDetails(id)
                if (gameDetailsResponse.isSuccessful) {
                    val gamesDetailsBody = gameDetailsResponse.body()!!
                    emit(
                        with(gamesDetailsBody) {
                            MenuItem(
                                title = name,
                                description = description,
                                imgUrl = backgroundImage,
                                id = id,
                                type = MenuType.Item
                            )
                        }.also {
                            gamesDao.addGame(it.toGameEntity())
                        }
                    )
                }
            }
        } else {
            gamesDao.getGames().map {
                MenuItem(
                    title = it.name,
                    id = it.id,
                    imgUrl = it.backgroundUrl,
                    description = it.description,
                    type = MenuType.Item
                )
            }.forEach { emit(it) }
        }
    }.catch {
        gamesDao.getGames().map {
            MenuItem(
                title = it.name,
                id = it.id,
                imgUrl = it.backgroundUrl,
                description = it.description,
                type = MenuType.Item
            )
        }.forEach { emit(it) }
    }
}