package com.example.fabrikatepla.presentation.ui.home

import CatalogTabAction
import PromoDialogTemplate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import stringToColor
import subBackgroundColor

@Composable
fun CatalogGridRecommendationRender(
    catalogGridRecommendation: List<CatalogCarouselRecom>,
    onClickItem: (String, Int) -> Unit
) {
    Column() {
        catalogGridRecommendation.forEach { carousel ->
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
            ) {
                carousel.name?.let {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = carousel.name ?: "",
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                carousel.catalogCarouselList?.let { carouselList ->
                    LazyRow(
                        contentPadding = PaddingValues(start = 16.dp)
                    ) {
                        items(carouselList) { item ->
                            Box(
                                modifier = Modifier
                                    .height(128.dp)
                                    .width((LocalConfiguration.current.screenWidthDp.dp - 72.dp) / 2)
                                    .padding(end = 8.dp)
                                    .background(
                                        getColor(item),
                                        RoundedCornerShape(16.dp)
                                    )
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {
                                        onClickItem.invoke(item.name ?: "", item.id)
                                    }
                            ) {
                                item.picture?.let {
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(width = 120.dp, height = 90.dp)
                                            .align(Alignment.BottomEnd),
                                        contentScale = ContentScale.Fit,
                                        model = it,
                                        contentDescription = null,
                                        alignment = Alignment.BottomEnd
                                    )
                                }
                                item.name?.let {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .padding(12.dp),
                                        text = it,
                                        color = item.style?.textColor.stringToColor(subText1Color())
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun getColor(item: CatalogCarouselItem): androidx.compose.ui.graphics.Color {
    return if (isSystemInDarkTheme()){
        item.style?.bgColor.stringToColorAdd32Alpha(subBackgroundColor())
    } else {
        item.style?.bgColor.stringToColor(subBackgroundColor())
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun CatalogGridRecommendationRenderPreviewNight() {
        CatalogGridRecommendationRender(
            getCatalogGridRecommendation().catalogCarousel!!,
            onClickItem = {headCategory, categoryId -> }
        )
}


@Composable
fun subText1Color() =
    if (isSystemInDarkTheme()) DarkThemeColors.SubText1 else LightThemeColors.SubText1



data class CatalogGridRecommendationResponse(
    val alertTemplate: PromoDialogTemplate? = null,
    val catalogCarousel: List<CatalogCarouselRecom>
)

data class CatalogCarouselRecom(
    val name: String? = null,
    val catalogCarouselList: List<CatalogCarouselItem>? = null
)

data class CatalogCarouselItem(
    val id: Int,
    val name: String? = null,
    val style: CatalogCarouselItemStyle? = null,
    val picture: String? = null,
    val action: CatalogTabAction? = null,
)

data class CatalogCarouselItemStyle(
    val bgColor: String? = null,
    val textColor: String? = null,
)

fun String?.stringToColorAdd32Alpha(defaultColor: Color): Color {
    return if (this != null && this.length == 7) {
        val color1 = this.replace("#", "").toLong(16).toInt()
        val color2 = "18041143".replace("#", "").toLong(16).toInt() // 18 -> 24% Alpha 041143 -> color
        val ratio = 0.5f
        val inverseRation = 1f - ratio
        val r: Float =  android.graphics.Color.red(color1) * ratio +  android.graphics.Color.red(color2) * inverseRation
        val g: Float =  android.graphics.Color.green(color1) * ratio +  android.graphics.Color.green(color2) * inverseRation
        val b: Float =  android.graphics.Color.blue(color1) * ratio +  android.graphics.Color.blue(color2) * inverseRation
        Color(android.graphics.Color.rgb(r.toInt(), g.toInt(), b.toInt()))
    } else {
        defaultColor
    }
}



fun getCatalogGridRecommendation() = CatalogGridRecommendationResponse(
    alertTemplate = null,
    catalogCarousel = listOf(
        CatalogCarouselRecom(
            name = "Фабрика Тепла рекомендует",
            catalogCarouselList = listOf(
                CatalogCarouselItem(
                    id = 15,
                    name = "Скидки и акции",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#065446",
                        textColor = "#ffffff"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    id = 16,
                    name = "Самые практичные нососы",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#F9AF44",
                        textColor = "#ffffff"
                    ),
                    picture = "https://cool-admin.coolclever.ru/upload/grid_tab/my_orders.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    id = 17,
                    name = "Скоро в продаже",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#b80404",
                        textColor = "#ffffff"
                    ),
                    picture = "https://cool-admin.coolclever.ru/upload/grid_tab/luch.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    id = 18,
                    name = "Это надо посмотреть!",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#042bb8",
                        textColor = "#ffffff"
                    ),
                    picture = "https://cool-admin.coolclever.ru/upload/grid_tab/novinki.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    id = 19,
                    name = "Коллекционные камины",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#9c6103",
                        textColor = "#ffffff"
                    ),
                    picture = "https://cool-admin.coolclever.ru/upload/grid_tab/hits.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
            )
        ),
        /*CatalogCarouselRecom(
            name = "КуулКлевер рекомендует",
            catalogCarouselList = listOf(
                CatalogCarouselItem(
                    name = "Скидки и акции",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#F9AF44",
                        textColor = "#ffffff"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    name = "Скидки и акции",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#F9AF44",
                        textColor = "#ffffff"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    name = "Скидки и акции",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#F9AF44",
                        textColor = "#ffffff"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    name = "Скидки и акции",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#F9AF44",
                        textColor = "#ffffff"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
                CatalogCarouselItem(
                    name = "Скидки и акции",
                    style = CatalogCarouselItemStyle(
                        bgColor = "#F9AF44",
                        textColor = "#ffffff"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png",
                    action = CatalogTabAction(
                        link = "coolclever://catalog/471293"
                    )
                ),
            )
        )*/
    )
)
