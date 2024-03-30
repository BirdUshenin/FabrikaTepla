package com.example.fabrikatepla.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabrikatepla.domain.ApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            fetchItems()
        }
    }

    private suspend fun fetchItems() {
        _state.update {
            it.copy(
                loading = true
            )
        }
        try {
            val response = ApiClient.apiService.getItems()
            _state.update {
                it.copy(
                    loading = false,
                    list = response
                )
            }
        } catch (e: Throwable) {
            _state.update {
                it.copy(
                    error = true
                )
            }
        }
    }
}