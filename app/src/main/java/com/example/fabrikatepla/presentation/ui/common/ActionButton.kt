package com.example.fabrikatepla.presentation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fabrikatepla.R

@Composable
fun ActionButton(
    buttonImage: Painter,
    buttonImageBackground: Color,
    buttonText: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        contentPadding = PaddingValues(4.dp),
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = buttonImage,
                contentDescription = stringResource(id = R.string.profile_my_bonuses),
                modifier = Modifier
                    .clip(CircleShape)
                    .background(buttonImageBackground)
                    .padding(10.dp)
            )
            Text(
                text = buttonText,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}
