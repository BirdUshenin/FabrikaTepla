package com.example.fabrikatepla.presentation.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fabrikatepla.R
import com.example.fabrikatepla.data.Profile
import com.example.fabrikatepla.presentation.ui.common.ActionButton
import com.example.fabrikatepla.presentation.ui.common.Loading

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
            cabinetMenu = state.cabinetMenu,
            supportInfo = state.supportInfo,
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun LoadedProfile(
    profile: Profile,
    cabinetMenu: List<CabinetMenuElement>,
    supportInfo: SupportInfo,
    paddingValues: PaddingValues,
) {
    val modifier = Modifier.padding(horizontal = 20.dp)

    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            profile.run {
                Row(modifier = modifier.padding(top = 20.dp)) {
                    AccountHolder(icon, name, surname)
                    Menu(supportInfo = supportInfo, modifier = modifier)
                }
                Spacer(modifier = modifier.size(30.dp))
                UserPlasticCard(discount, balance, code, modifier)
            }

            Spacer(modifier = modifier.size(50.dp))
            ProfileActionButtons(modifier = modifier)

            Spacer(modifier = modifier.size(40.dp))
            Text(
                text = stringResource(id = R.string.profile_my_cabinet),
                modifier = modifier,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
            Spacer(modifier = Modifier.size(10.dp))
        }

        items(cabinetMenu) { item ->
            CabinetMenuElement(cabinetMenuElement = item, modifier = modifier)
        }
    }
}

@Composable
private fun ProfileActionButtons(
    modifier: Modifier = Modifier,
) {
    var bonusesButtonClicked by rememberSaveable { mutableStateOf(false) }
    val bonusesButtonBackground =
        if (bonusesButtonClicked) Color(0xFFFA6C37) else Color.Black
    val bonusesButtonText =
        if (bonusesButtonClicked) "123456" else stringResource(id = R.string.profile_my_bonuses)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        ActionButton(
            buttonImage = painterResource(id = R.drawable.sync),
            buttonImageBackground = bonusesButtonBackground,
            buttonText = bonusesButtonText,
            onClick = { bonusesButtonClicked = !bonusesButtonClicked },
        )
        ActionButton(
            buttonImage = painterResource(id = R.drawable.like),
            buttonImageBackground = Color.Black,
            buttonText = stringResource(id = R.string.profile_favorite),
            onClick = { /* TODO */ },
        )
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
    val cabinetMenu = listOf(
        CabinetMenuElement.ClickableElement("мои покупки"),
        CabinetMenuElement.ClickableElement("избранное"),
        CabinetMenuElement.ClickableElement("подписки и уведомления"),
        CabinetMenuElement.ClickableElement("купить подарочную карту"),
        CabinetMenuElement.Space,
        CabinetMenuElement.ClickableElement("доставка и оплата"),
        CabinetMenuElement.ClickableElement("документы"),
        CabinetMenuElement.ClickableElement("вакансии"),
        CabinetMenuElement.Space,
        CabinetMenuElement.ClickableElement("мои адреса"),
        CabinetMenuElement.ClickableElement("Россия"),
    )
    val supportInfo = SupportInfo(supportNumber = "8 800 770 70 21", appVersion = "5.01.1")
    LoadedProfile(profile, cabinetMenu, supportInfo, PaddingValues())
}
