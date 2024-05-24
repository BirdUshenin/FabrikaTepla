package com.example.fabrikatepla.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fabrikatepla.R
import com.example.fabrikatepla.ui.theme.ProfileIconBackground

@Composable
fun RowScope.AccountHolder(
    icon: String?,
    name: String,
    surname: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        val imageModifier = modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(color = ProfileIconBackground)
            .padding(8.dp)

        icon?.let {
            AsyncImage(
                model = icon,
                contentDescription = stringResource(id = R.string.profile_avatar),
                modifier = imageModifier,
                contentScale = ContentScale.Crop,
            )
        } ?: run {
            Image(
                painter = painterResource(R.drawable.person),
                contentDescription = stringResource(id = R.string.profile_avatar),
                modifier = imageModifier,
                contentScale = ContentScale.Crop,
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy((-5).dp)) {
            Text(text = name, style = MaterialTheme.typography.titleSmall)
            Text(text = surname, style = MaterialTheme.typography.titleSmall)
        }
    }
}
