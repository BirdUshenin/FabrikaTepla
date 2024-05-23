package com.example.fabrikatepla.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fabrikatepla.R
import com.example.fabrikatepla.data.Profile
import com.example.fabrikatepla.presentation.ui.common.Loading
import com.example.fabrikatepla.ui.theme.ProfileIconBackground

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    paddingValues: PaddingValues,
) {
    val state by viewModel.state.collectAsState()

    if (state.loading) {
        Loading()
    } else {
        LoadedProfile(
            profile = state.profile,
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun LoadedProfile(
    profile: Profile,
    paddingValues: PaddingValues,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
            )
            .then(Modifier.padding(paddingValues))
            .verticalScroll(state = scrollState)
    ) {
        profile.run {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                AccountHolder(icon, name, surname)
                Menu()
            }
            Surface(modifier = Modifier.padding(top = 30.dp)) {
                UserPlasticCard(discount, balance, code)
            }
        }
    }
}

@Composable
private fun RowScope.AccountHolder(
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

@Composable
private fun Menu() {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.profile_menu),
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.profile_settings)) },
                onClick = { /* Handle settings */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = null
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadedProfilePreview() {
    val profile = Profile(
        id = 90,
        name = "Александр",
        surname = "Румянцев",
        discount = "25%",
        balance = "150",
        code = "578-3414-4143-414",
        icon = "https://cdn-0.emojis.wiki/emoji-pics/messenger/doughnut-messenger.png"
    )
    LoadedProfile(profile, PaddingValues())
}
