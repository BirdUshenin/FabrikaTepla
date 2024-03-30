package com.example.fabrikatepla.presentation.ui.home.CategoryItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabrikatepla.domain.ApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _state: MutableStateFlow<CategoryScreenState> = MutableStateFlow(CategoryScreenState())
    val state = _state.asStateFlow()
    private val _id = MutableStateFlow(0)
    private val id: StateFlow<Int> = _id

    init {
        viewModelScope.launch {
            id.collect { _ ->
                categoryItemsRequest()
                delay(3000)
            }
        }
    }

    private suspend fun categoryItemsRequest() {
        _state.update {
            it.copy(
                loading = true, list = listOf()
            )
        }
        try {
            val response = when (id.value) {
                1 -> ApiClient.apiService.getBoilers()
                2 -> ApiClient.apiService.getRadiators()
                3 -> ApiClient.apiService.getWaterHeaters()
                4 -> ApiClient.apiService.getPumps()
                5 -> ApiClient.apiService.getPipes()
                6 -> ApiClient.apiService.getFloors()
                else -> throw IllegalArgumentException("Unexpected id value: ${id.value}")
            }
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
    fun updateId(newId: Int) {
        _id.value = newId
    }
}

