package com.example.fabrikatepla.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fabrikatepla.R
import com.example.fabrikatepla.common.SizeConstants.ASPECT_RATIO_OF_PLASTIC_CARD
import com.example.fabrikatepla.presentation.ui.common.Loading
import com.example.fabrikatepla.ui.theme.ProfileCardBackground
import com.example.fabrikatepla.ui.theme.ProfileIconBackground

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    paddingValues: PaddingValues,
) {
    val state by viewModel.state.collectAsState()

    val scrollState = rememberScrollState()
    val profile = state.profile

    if (state.loading) {
        Loading()
    } else {
        Column(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                )
                .then(Modifier.padding(paddingValues))
                .verticalScroll(state = scrollState)
        ) {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                profile.run {
                    AccountHolder(icon, name, surname)
                }
                Menu()
            }
            profile.run {
                Surface(modifier = Modifier.padding(top = 30.dp)) {
                    UserCard(discount, balance, code)
                }
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

        if (icon != null) {
            AsyncImage(
                model = icon,
                contentDescription = stringResource(id = R.string.profile_avatar),
                modifier = imageModifier,
                contentScale = ContentScale.Crop,
            )
        } else {
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
fun Menu() {
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

@Composable
fun UserCard(
    discount: String,
    balance: String,
    code: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .aspectRatio(ASPECT_RATIO_OF_PLASTIC_CARD),
        onClick = { /* TODO */ },
        colors = CardDefaults.cardColors().copy(containerColor = ProfileCardBackground)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    top = 10.dp,
                    end = 10.dp,
                    bottom = 15.dp,
                ),
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy((-5).dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.profile_card_discount),
                        color = Color.Black,
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                    )
                    Text(
                        text = discount,
                        color = Color.Black,
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = stringResource(id = R.string.profile_card_info),
                    tint = Color.LightGray,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .size(30.dp)
                        .clickable { /* TODO */ }
                )
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.profile_car_balance),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = balance,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    )
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.height(35.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.profile_car_qr_code),
                        color = Color.Black,
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun UserCardPreview(
    modifier: Modifier = Modifier,
) {
    Row {
        UserCard(
            discount = "25%",
            balance = "150",
            code = "578-3414-4143-414"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountHolderAndMenuPreview(
    modifier: Modifier = Modifier,
) {
    Row {
        AccountHolder(
            icon = null,
            name = "Александр",
            surname = "Румянцев"
        )
        Menu()
    }
}


