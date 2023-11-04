package com.example.royaal.domain.usecases

import com.example.royaal.common.asResult
import com.example.royaal.domain.MainScreenRepository
import javax.inject.Inject

class LoadPromotionsUseCase @Inject constructor(
    private val repo: MainScreenRepository
) {

    operator fun invoke() = repo.getPromotions().asResult()

}