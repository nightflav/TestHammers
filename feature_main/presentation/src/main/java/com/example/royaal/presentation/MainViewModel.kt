package com.example.royaal.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royaal.common.fetchResult
import com.example.royaal.common.model.MenuItem
import com.example.royaal.common.model.MenuType
import com.example.royaal.domain.usecases.LoadGamesUseCase
import com.example.royaal.domain.usecases.LoadPromotionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.random.Random

class MainViewModel @Inject constructor(
    private val loadPromotionsUseCase: LoadPromotionsUseCase,
    private val loadGamesUseCase: LoadGamesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()
    private val currState
        get() = state.value

    init {
        loadPromotions()
        loadGames()
    }

    fun loadPromotions() {
        viewModelScope.launch {
            loadPromotionsUseCase().collect {
                it.fetchResult(
                    onLoading = {

                    },
                    onError = {

                    },
                    onSuccess = { proms ->
                        _state.emit(
                            currState.copy(
                                promotions = proms,
                                isLoading = false,
                                error = null
                            )
                        )
                    }
                )
            }
        }
    }

    fun loadGames() {
        viewModelScope.launch {
            loadGamesUseCase()
                .collect { newGame ->
                    _state.emit(
                        currState.copy(
                            games = (currState.games + newGame).addSeparators(),
                            isLoading = false,
                            error = null
                        )
                    )
                }
        }
    }

    private fun List<MenuItem>.addSeparators(): List<MenuItem> {
        val result = mutableListOf<MenuItem>()
        var count = 0
        for (i in indices) {
            if (get(i).type == MenuType.Separator)
                continue
            count++
            if (count % 3 == 0) {
                result.add(
                    MenuItem.Empty.copy(
                        type = MenuType.Separator,
                        title = Random(LocalDateTime.now().hashCode()).nextInt().toString()
                    )
                )
            }
            result.add(get(i))
        }
        return result
    }

}