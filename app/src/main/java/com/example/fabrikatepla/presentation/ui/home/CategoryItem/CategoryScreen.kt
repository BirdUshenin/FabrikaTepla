package com.example.fabrikatepla.presentation.ui.home.CategoryItem

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.fabrikatepla.R
import com.example.fabrikatepla.data.CategoryItem
import com.example.fabrikatepla.data.Item
import com.example.fabrikatepla.presentation.ui.home.HomeScreenState

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
                            actions = {
                                IconButton(onClick = { /* doSomething() */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                    },
                ) {

                    LazyColumn(
                        state = rememberStateScroll,
                        modifier = Modifier.padding(paddingValues),
                        contentPadding = it
                    ) {
                        item {
                            Text(
                                text = "Категории",
                                modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 15.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp
                            )
                        }
                        state.list.forEach { item ->
                            item {
                                Commodity(
                                    item = item,
                                    onItemClick = {
                                        selectedItem.value = it
                                        viewModel.updateId(it.id)
                                    }
                                )
                            }
                        }
                        item {
                            Text(
                                text = "Горячие скидки",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 50.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Commodity(
    item: Item,
    onItemClick: (Item) -> Unit
) {
    Card(
        modifier = Modifier.padding(5.dp)
    ){
        Card(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            onClick = { onItemClick(item) }
        ) {
            Title(item = item)
            Image(
                painter = rememberAsyncImagePainter(
                    item.imageSrc
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentDescription = null
            )
            Box(Modifier.padding(15.dp)) {
                Text(text = item.description,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Composable
fun Title(item: Item) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.name,
                color = Color(0xFFFA6C37),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Image(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null
        )
    }
}