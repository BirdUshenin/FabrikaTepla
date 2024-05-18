package com.example.fabrikatepla.presentation.ui.home.CategoryItem

import CatalogGridHotKeysRender
import DarkThemeColors.SubText1
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.fabrikatepla.R
import com.example.fabrikatepla.data.CategoryItem
import com.example.fabrikatepla.data.Item
import com.example.fabrikatepla.presentation.ui.home.CatalogGridRecommendationRender
import com.example.fabrikatepla.presentation.ui.home.CatalogGridRootSectionsRender
import com.example.fabrikatepla.presentation.ui.home.HomeScreenState
import com.example.fabrikatepla.presentation.ui.home.getCatalogGridRecommendation
import com.example.fabrikatepla.presentation.ui.home.getCatalogGridSections
import getCatalogGridHotKeys

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CategoryScreen(
    selectedItem: MutableState<Item?>,
    navigateBack: () -> Unit,
    state: HomeScreenState,
    paddingValues: PaddingValues,
    rememberStateScroll: LazyListState,
    viewModel: CategoryViewModel
) {
    val stateCategory by viewModel.state.collectAsState()
    val selectedCategoryItem = remember { mutableStateOf<CategoryItem?>(null) }

    if (selectedItem.value != null) {
        CategoryProduct(
            selectedItem.value!!,
            navigateBack,
            stateCategory,
            selectedCategoryItem,
            paddingValues
        )
    } else {
        when {
            state.error -> {
                Text(text = "Error")
            }

            state.loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFFA6C37)),
                            title = {
                                Text(
                                    "Фабрика Тепла",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { /* doSomething() */ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.logo),
                                        contentDescription = "Localized description",
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            },
//                            actions = {
//                                IconButton(onClick = { /* doSomething() */ }) {
//                                    Icon(
//                                        imageVector = Icons.Filled.Favorite,
//                                        contentDescription = "Localized description"
//                                    )
//                                }
//                            }
                        )
                    },
                ) {
                    LazyColumn(
                        state = rememberStateScroll,
                        modifier = Modifier.padding(paddingValues),
                        contentPadding = it
                    ) {
                        item {
                            Column(
                                modifier = Modifier.padding(top = 10.dp)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(
                                            start = 15.dp,
                                            bottom = 10.dp
                                        ),
                                    text = "Популярное",
                                    fontSize = 14.sp
                                )
                                CatalogGridHotKeysRender(
                                    listHotkeys = getCatalogGridHotKeys().catalogTab,
                                    state = state,
                                    onItemClick = {
                                        selectedItem.value = it
                                        viewModel.updateId(it.id)
                                    }
                                )
                                CatalogGridRecommendationRender(
                                    getCatalogGridRecommendation().catalogCarousel,
                                    onClickItem = { headCategory, categoryId -> }
                                )
                                CatalogGridRootSectionsRender(
                                    getCatalogGridSections().catalogCarousel,
                                    onClickItem = { item -> },
                                    onClickShowAllInSection = { id, name -> }
                                )
                                ContinueBoxes()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ContinueBoxes() {
    Column {
        Text(
            modifier = Modifier
                .padding(start = 15.dp),
            text = "Все категории",
            fontSize = 14.sp
        )
        Column (
            modifier = Modifier
                .height(700.dp)
                .padding(3.dp)
            ,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Boxes()
            Boxes()
            Boxes()
            Boxes()
            Boxes()
        }
    }

}


@Composable
fun Boxes() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(120.dp)
                .width(180.dp)
                .clickable {}
                .background(SubText1, RoundedCornerShape(16.dp)),
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(120.dp)
                .width(180.dp)
                .clickable {}
                .background(SubText1, RoundedCornerShape(16.dp)),
        )
    }
}