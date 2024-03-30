package com.example.fabrikatepla.presentation.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fabrikatepla.data.Item
import com.example.fabrikatepla.presentation.ui.home.CategoryItem.CategoryScreen
import com.example.fabrikatepla.presentation.ui.home.CategoryItem.CategoryViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    categoryViewModel: CategoryViewModel,
) {
    val state by viewModel.state.collectAsState()
    val selectedItem = remember { mutableStateOf<Item?>(null) }
    val navigateBack: () -> Unit = {
        selectedItem.value = null
    }
    val rememberStateScroll = rememberLazyListState()

    CategoryScreen(selectedItem, navigateBack, state, paddingValues, rememberStateScroll, categoryViewModel)

}