package com.example.fabrikatepla.presentation.ui.home.CategoryItem

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.fabrikatepla.R
import com.example.fabrikatepla.data.CategoryItem
import com.example.fabrikatepla.data.Item
import com.example.fabrikatepla.presentation.ui.common.ActionButton
import com.example.fabrikatepla.presentation.ui.common.Loading

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CategoryProduct(
    item: Item,
    onBackPressed: () -> Unit,
    state: CategoryScreenState,
    selectedItem: MutableState<CategoryItem?>,
    paddingValues: PaddingValues,
) {
    val isLoading by remember(state.loading) {
        mutableStateOf(state.loading)
    }

    if (isLoading) {
        Loading()
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFFFA6C37)),
                    title = {
                        Text(
                            text = item.name,
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
                )
            }
        ) {
            Column (
                modifier = Modifier
                    .padding(top = 15.dp)
                    .background(Color.LightGray)
            ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = it,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    state.list.forEach { item ->
                        item {
                            CategoryProductList(
                                item = item,
                                onItemClick = { selectedItem.value = it }
                            )
                        }
                    }
                }


//                LazyColumn(
//                    modifier = Modifier.padding(paddingValues),
//                    contentPadding = it,
//                ) {
//                    val chunkedItems = state.list.chunked(2)
//                    chunkedItems.forEach { chunk ->
//                        item {
//                            Row(Modifier.fillMaxWidth()) {
//                                chunk.forEach { item ->
//                                    Column(Modifier.weight(1f)) {
//                                        CategoryProductList(
//                                            item = item,
//                                            onItemClick = { selectedItem.value = it }
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }

//                LazyColumn(
//                    contentPadding = it,
//                ) {
//                    val chunkedItems = state.list.chunked(2)
//                    chunkedItems.forEach { chunk ->
//                        item {
//                            FlowRow(Modifier.fillMaxWidth()) {
//                                chunk.forEach { item ->
//                                    Column(Modifier.weight(1f)) {
//                                        CategoryProductList(
//                                            item = item,
//                                            onItemClick = { selectedItem.value = it }
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }

//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(2),
//                    contentPadding = it,
//                    modifier = Modifier.padding(paddingValues),
//                    verticalArrangement = Arrangement.spacedBy(8.dp), // Расстояние между строками
//                    horizontalArrangement = Arrangement.SpaceBetween // Выравнивание элементов по ширине
//                ) {
//                    state.list.forEach { item ->
//                        item {
//                            CategoryProductList(
//                                item = item,
//                                onItemClick = { selectedItem.value = it }
//                            )
//                        }
//                    }
//                }


            }
        }
        BackHandler {
            onBackPressed()
        }
    }
}

@Composable
fun CategoryProductList(
    item: CategoryItem,
    onItemClick: (CategoryItem) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .padding(bottom = 15.dp)
    ){
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
//            colors = CardDefaults.cardColors(containerColor = Color.Blue),
            onClick = { onItemClick(item) }
        ) {
            Box(Modifier.padding(15.dp)) {
                Text(text = item.name)
            }
            Image(
                painter = rememberAsyncImagePainter(
                    item.imageSrc
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentDescription = null
            )
            Column (
                modifier = Modifier.fillMaxWidth().padding(5.dp),
            ) {
                Text(text = item.price, fontSize = 25.sp)
                Box(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)){
                    ActionButton(
                        buttonImage = painterResource(id = R.drawable.like),
                        buttonImageBackground = Color.Black,
                        buttonText = stringResource(id = R.string.profile_favorite),
                    ) { /* TODO */ }
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFA6C37)),
                    modifier = Modifier.height(50.dp).width(155.dp)
                ) {
                    Text(text = "Купить")
                }
            }
        }
    }
}

@Preview
@Composable
fun Watch() {
    val item = CategoryItem(
        id = 4,
        name = "Радиаторы стальные",
        imageSrc = "",
        price = ""
    )
    Column {
        Row {
            CategoryProductList(
                onItemClick = {},
                item = item
            )
            CategoryProductList(
                onItemClick = {},
                item = item
            )
        }
        Row {
            CategoryProductList(
                onItemClick = {},
                item = item
            )
            CategoryProductList(
                onItemClick = {},
                item = item
            )
        }
    }
}
