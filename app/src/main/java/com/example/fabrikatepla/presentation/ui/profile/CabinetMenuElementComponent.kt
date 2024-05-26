package com.example.fabrikatepla.presentation.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fabrikatepla.R


@Composable
fun CabinetMenuElement(
    cabinetMenuElement: CabinetMenuElement,
    modifier: Modifier = Modifier,
) {
    when (cabinetMenuElement) {
        is CabinetMenuElement.ClickableElement -> {
            OutlinedCard(
                onClick = cabinetMenuElement.onClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                border = BorderStroke(0.dp, Color.Transparent),
            ) {
                Row(
                    modifier = modifier.padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    cabinetMenuElement.image?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clip(CircleShape)
                                .size(12.dp)
                        )
                    }
                    Text(
                        text = cabinetMenuElement.title,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Icon(
                        painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.KeyboardArrowRight),
                        contentDescription = stringResource(
                            id = R.string.profile_my_cabinet_arrow
                        )
                    )
                }
            }
        }

        is CabinetMenuElement.Space -> Spacer(modifier = Modifier.size(25.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CabinetMenuElementPreview() {
    val cabinetMenuElement = CabinetMenuElement.ClickableElement("мои покупки")
    CabinetMenuElement(cabinetMenuElement)
}
