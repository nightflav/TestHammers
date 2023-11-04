package com.example.royaal.domain.usecases

import com.example.royaal.domain.MainScreenRepository
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

class LoadGamesUseCase @Inject constructor(
    private val repo: MainScreenRepository
) {

    operator fun invoke() = repo.getGames()

}