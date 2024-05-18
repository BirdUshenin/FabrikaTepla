package com.example.fabrikatepla.presentation.ui.home

import com.example.fabrikatepla.data.Item

data class HomeScreenState(
    val loading: Boolean = true,
    val list: List<Item> = emptyList(),
    val error: Boolean = false
)