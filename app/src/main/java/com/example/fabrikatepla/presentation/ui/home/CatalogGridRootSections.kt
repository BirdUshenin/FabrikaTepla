package com.example.fabrikatepla.presentation.ui.home

import PromoDialogTemplate
import stringToColor
import subBackgroundColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun CatalogGridRootSectionsRender(
    catalogGridSections: List<CatalogGridSection>,
    onClickItem: (CatalogGridSectionItem) -> Unit,
    onClickShowAllInSection: (Int, String) -> Unit
) {
    if (catalogGridSections.isNotEmpty()) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
                .padding(
                    horizontal = 15.dp,
                    vertical = 24.dp
                )
        ) {
            catalogGridSections.forEach { section ->
                section.name?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 16.dp),
                            text = it,
                        )
                        Text(
                            modifier = Modifier
                                .background(subBackgroundColor(), RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    onClickShowAllInSection.invoke(section.id ?: -1, it)
                                }
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            text = "Показать все",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (section.catalogCarouselList?.isNotEmpty() == true) {
                    Row(
                    )
                    {
                        section.catalogCarouselList?.forEach {
                            SectionRender(
                                item = it,
                                onClick = onClickItem
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun SectionRender(
    item: CatalogGridSectionItem,
    onClick: (CatalogGridSectionItem) -> Unit,
) {
    val size = item.style?.size
    val width = when (size) {
        CatalogGridSectionItemSize.XL -> {
            LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp
        }

        CatalogGridSectionItemSize.L -> {
            ((LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp - 12.dp - ((LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp - 12.dp - 12.dp) / 3).value.toInt().dp))
        }

        CatalogGridSectionItemSize.M -> {
            (LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp - 12.dp) / 2
        }

        CatalogGridSectionItemSize.S -> {
            ((LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp - 12.dp - 12.dp) / 3).value.toInt().dp
        }

        else -> {
            LocalConfiguration.current.screenWidthDp.dp - 16.dp
        }
    }

    CategorySection(
        item = item,
        onClick = onClick,
        width = width
    )
}

@Composable
fun CustomSection(
    item: CatalogGridSectionItem,
    onClick: (CatalogGridSectionItem) -> Unit,
    width: Dp
) {
    Box(
        modifier = Modifier
            .height(128.dp)
            .width(width)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                item.id?.let { onClick.invoke(item) }
            }
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(width = width, height = 128.dp),
            model = item.picture,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            loading = {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(subBackgroundColor()),
                )
            },
            error = {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(subBackgroundColor()),
                )
            }
        )
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

@Composable
fun CategorySection(
    item: CatalogGridSectionItem,
    onClick: (CatalogGridSectionItem) -> Unit,
    width: Dp
) {
    Column (modifier = Modifier.padding(5.dp)){
        Box(
            modifier = Modifier
                .height(120.dp)
                .width(width)
                .background(
                    getColor(item),
                    RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    item.id?.let { onClick.invoke(item) }
                }
        ) {
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

@Composable
private fun getColor(item: CatalogGridSectionItem): androidx.compose.ui.graphics.Color {
    return if (isSystemInDarkTheme()) {
        item.style?.bgColor.stringToColorAdd32Alpha(subBackgroundColor())
    } else {
        item.style?.bgColor.stringToColor(subBackgroundColor())
    }
}


@Preview(
    showBackground = true
)
@Composable
fun CatalogGridRootSectionsRenderPreview() {
        CatalogGridRootSectionsRender(
            getCatalogGridSections().catalogCarousel!!,
            onClickItem = { item -> },
            onClickShowAllInSection = { id, name -> }
        )
}

data class CatalogGridSectionsResponse(
    val alertTemplate: PromoDialogTemplate? = null,
    val catalogCarousel: List<CatalogGridSection>
)

data class CatalogGridSection(
    val id: Int? = null,
    val name: String? = null,
    val catalogCarouselList: List<CatalogGridSectionItem>? = null
)

data class CatalogGridSectionItem(
    val id: Int? = null,
    val name: String? = null,
    val style: CatalogGridSectionItemStyle? = null,
    val picture: String? = null,
    val actionLink: String? = null
)

data class CatalogGridSectionItemStyle(
    val size: CatalogGridSectionItemSize,
    val bgColor: String? = null,
    val textColor: String? = null,
)


enum class CatalogGridSectionItemSize {
    XL,
    L,
    M,
    S
}

fun CatalogGridSectionItemSize.toCatalogGridSectionItemSizeUIO(): CatalogGridSectionItemSizeUIO {
    return when (this) {
        CatalogGridSectionItemSize.XL -> CatalogGridSectionItemSizeUIO.XL
        CatalogGridSectionItemSize.L -> CatalogGridSectionItemSizeUIO.L
        CatalogGridSectionItemSize.M -> CatalogGridSectionItemSizeUIO.M
        CatalogGridSectionItemSize.S -> CatalogGridSectionItemSizeUIO.S
    }
}

enum class CatalogGridSectionItemSizeUIO {
    XL {
        @Composable
        fun getWidthCell(): Float {
            return (LocalView.current.width - 32).toFloat()
        }

        @Composable
        fun getWidthCellDp(): Dp {
            return LocalConfiguration.current.screenWidthDp.dp - 32.dp
        }

        fun getIconSize() = DpSize(width = 107.dp, height = 80.dp)
    },
    L {
        @Composable
        fun getWidthCell(): Float {
            return ((LocalView.current.width - 16 - 16 - 12) / 3 * 2).toFloat()
        }

        @Composable
        fun getWidthCellDp(): Dp {
            return (LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp - 12.dp) / 3 * 2
        }

         fun getIconSize() = DpSize(width = 107.dp, height = 80.dp)
    },
    M {
        @Composable
         fun getWidthCell(): Float {
            return ((LocalView.current.width - 16 - 16 - 12) / 2).toFloat()
        }

        @Composable
         fun getWidthCellDp(): Dp {
            return (LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp - 12.dp) / 2
        }

         fun getIconSize() = DpSize(width = 107.dp, height = 80.dp)
    },
    S {
        @Composable
         fun getWidthCell(): Float {
            return ((LocalView.current.width - 16 - 16 - 12 - 12) / 3).toFloat()
        }

        @Composable
         fun getWidthCellDp(): Dp {
            return (LocalConfiguration.current.screenWidthDp.dp - 16.dp - 16.dp - 12.dp - 12.dp) / 3
        }

         fun getIconSize() = DpSize(width = 107.dp, height = 80.dp)
    };
}


fun getCatalogGridSections() = CatalogGridSectionsResponse(
    alertTemplate = null,
    catalogCarousel = listOf(
        CatalogGridSection(
            name = "Надежность и простота",
            catalogCarouselList = listOf(
                CatalogGridSectionItem(
                    id = 470165,
                    name = "Стальные трубы",
                    style = CatalogGridSectionItemStyle(
                        size = CatalogGridSectionItemSize.L,
                        bgColor = "#FADCD5",
                        textColor = "#000000"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png"
                ),
                CatalogGridSectionItem(
                    id = 471293,
                    name = "Прочные веревки",
                    style = CatalogGridSectionItemStyle(
                        size = CatalogGridSectionItemSize.S,
                        bgColor = "#FADCD5",
                        textColor = "#000000"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png"
                ),
            )
        ),
        CatalogGridSection(
            name = "Смотрите также",
            catalogCarouselList = listOf(
                CatalogGridSectionItem(
                    id = 470165,
                    name = "Теплые полы",
                    style = CatalogGridSectionItemStyle(
                        size = CatalogGridSectionItemSize.XL,
                        bgColor = "#FADCD5",
                        textColor = "#000000"
                    ),
                    picture = "https://www.coolclever.ru/img_kglb/11857.png"
                ),
            )
        )
    )
)
