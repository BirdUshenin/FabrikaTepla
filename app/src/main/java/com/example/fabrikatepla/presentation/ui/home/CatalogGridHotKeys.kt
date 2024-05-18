
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fabrikatepla.data.Item
import com.example.fabrikatepla.presentation.ui.home.HomeScreenState
import java.io.Serializable


@Composable
fun CatalogGridHotKeysRender(
    listHotkeys: List<CatalogTab>,
    state: HomeScreenState,
    onItemClick: (Item) -> Unit
) {

    LazyRow(contentPadding = PaddingValues(start = 16.dp)) {
        items(listHotkeys) { catalogTab ->
            state.list.forEach { item ->
                Box(
                    modifier = Modifier
                        .height(88.dp)
                        .width((LocalConfiguration.current.screenWidthDp.dp - 32.dp) / 2)
                        .padding(end = 8.dp)
                        .background(
                            catalogTab.style?.bgColor.stringToColor(subBackgroundColor()),
                            RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            catalogTab.action?.link?.let {
                                onItemClick(item)
                            }
                        }
                ) {
                    val colorStart =
                        Color(android.graphics.Color.parseColor(catalogTab.style?.bgColor))
                    val colorEnd = Color.White.copy(alpha = 0.2f)
                    Canvas(
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.BottomEnd),
                        onDraw = {
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        colorStart,
                                        colorEnd
                                    ),
                                    center = Offset(size.width * 0.65f, size.height * 0.65f)
                                ),
                                radius = size.height / 1.9090909f,
                                center = Offset(size.width * 0.65f, size.height * 0.65f)
                            )
                        }
                    )
                    item.imageSrc.let {
                        AsyncImage(
                            modifier = Modifier
                                .padding(12.dp)
                                .size(32.dp)
                                .align(Alignment.BottomEnd),
                            contentScale = ContentScale.Fit,
                            model = it,
                            contentDescription = null
                        )
                    }
                    item.name?.let {
                        Text(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp),
                            text = it,
                            color = catalogTab.style?.textColor.stringToColor(Color.White)
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CatalogGridHotKeysRenderPreview() {
    val state = HomeScreenState()
    CatalogGridHotKeysRender(
        listHotkeys = getCatalogGridHotKeys().catalogTab!!,
        onItemClick = { },
        state = state
    )
}

data class CatalogTab(
    val name: String? = null,
    val style: CatalogTabStyle? = null,
    val picture: String? = null,
    val action: CatalogTabAction? = null,
)

data class CatalogTabStyle(
    val bgColor: String? = null,
    val textColor: String? = null,
)

data class CatalogTabAction(
    val link: String? = null,
)

fun String?.stringToColor(defaultColor: Color): Color {
    return if (this != null && this.length == 7) {
        Color(android.graphics.Color.parseColor(this))
    } else {
        defaultColor
    }
}
@Composable
fun subBackgroundColor() =
    if (isSystemInDarkTheme()) DarkThemeColors.SubBackground else LightThemeColors.SubBackground

object DarkThemeColors {
    val Background = Color(0xFF1E1E1E)
    val BackgroundSnackBar = Color(0xFF35383D)
    val SubBackground = Color(0xFF2C2D2E)
    val SubText1 = Color(0xFFD6D6D6)
}
object LightThemeColors {
    val Background = Color(0xFFFFFFFF)
    val BackgroundSnackBar = Color(0xFF383A3F)
    val SubBackground = Color(0xFFF7F7F7)
    val SubText1 = Color(0xFF4F4F4F)
}


fun getCatalogGridHotKeys() = CatalogGridHotkeysResponse(
    alertTemplate = null,
    catalogTab = listOf(
        CatalogTab(
            name = "Мой ЛУЧ",
            style = CatalogTabStyle(
                bgColor = "#F9AF44",
                textColor = "#ffffff"
            ),
            picture = "https://www.coolclever.ru/img_kglb/11857.png",
            action = CatalogTabAction(
                link = "coolclever://catalog/471293"
            )
        ),
        CatalogTab(
            name = "Хиты",
            style = CatalogTabStyle(
                bgColor = "#F66B1F",
                textColor = "#ffffff"
            ),
            picture = "https://www.coolclever.ru/img_kglb/11857.png",
            action = CatalogTabAction(
                link = "coolclever://catalog/471293"
            )
        ),
        CatalogTab(
            name = "Мой ЛУЧ",
            style = CatalogTabStyle(
                bgColor = "#F9AF44",
                textColor = "#ffffff"
            ),
            picture = "https://www.coolclever.ru/img_kglb/11857.png",
            action = CatalogTabAction(
                link = "coolclever://catalog/471293"
            )
        ),
        CatalogTab(
            name = "Хиты",
            style = CatalogTabStyle(
                bgColor = "#F66B1F",
                textColor = "#ffffff"
            ),
            picture = "https://www.coolclever.ru/img_kglb/11857.png",
            action = CatalogTabAction(
                link = "coolclever://catalog/471293"
            )
        ),
    )
)
data class CatalogGridHotkeysResponse(
    val alertTemplate: PromoDialogTemplate? = null,
    val catalogTab: List<CatalogTab>
)
data class PromoDialogTemplate(
    val id: Int,
    val title: String? = null,
    val subtitle: String? = null,
    val description: String? = null,
    val buttonText: String? = null,
    val image: String? = null,
    val actionClick: String? = null,
    val symbolLink: String? = null
): Serializable
