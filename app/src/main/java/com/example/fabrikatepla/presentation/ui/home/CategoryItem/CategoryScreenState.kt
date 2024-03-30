package com.example.fabrikatepla.presentation.ui.home.CategoryItem

import com.example.fabrikatepla.data.CategoryItem

data class CategoryScreenState (
    val loading: Boolean = true,
    val list: List<CategoryItem> = emptyList(),
    val error: Boolean = false
)
