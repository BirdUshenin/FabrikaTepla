package com.example.fabrikatepla.presentation.ui.profile

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fabrikatepla.R
import com.example.fabrikatepla.common.SizeConstants
import com.example.fabrikatepla.ui.theme.ProfilePlasticCardBackground
import kotlinx.coroutines.delay


@Composable
fun UserPlasticCard(
    discount: String,
    balance: String,
    code: String,
    modifier: Modifier = Modifier,
) {
    var qrCodeIsNotActive by rememberSaveable { mutableStateOf(true) }
    var showFrontSide by rememberSaveable { mutableStateOf(true) }

    val endOfRotation = 180f
    val rotationDuration = 500

    val targetRotationValue = if (!qrCodeIsNotActive) endOfRotation else 0f
    val rotation by animateFloatAsState(
        targetValue = targetRotationValue,
        animationSpec = tween(durationMillis = rotationDuration, easing = LinearEasing),
        label = "rotate user plastic card",
    )

    LaunchedEffect(qrCodeIsNotActive) {
        // Delay is used to change the content of the card in the middle of animation
        delay(rotationDuration.toLong() / 2)
        showFrontSide = qrCodeIsNotActive
    }

    val onQrCodeClick = {
        qrCodeIsNotActive = !qrCodeIsNotActive
        showFrontSide = !qrCodeIsNotActive
    }

    Card(
        modifier = Modifier
            .clipToBounds()
            .then(modifier)
            .padding(5.dp)
            .fillMaxWidth()
            .aspectRatio(SizeConstants.ASPECT_RATIO_OF_PLASTIC_CARD)
            .graphicsLayer {
                rotationY = rotation
            },
        onClick = onQrCodeClick,
        colors = CardDefaults.cardColors().copy(containerColor = ProfilePlasticCardBackground)
    ) {
        if (showFrontSide) {
            UserPlasticCardFrontSide(
                discount = discount,
                balance = balance,
                onQrCodeClick = onQrCodeClick,
                modifier = modifier,
            )
        } else {
            Surface(
                modifier = modifier.graphicsLayer { rotationY = endOfRotation },
                color = ProfilePlasticCardBackground,
            ) {
                UserPlasticCardBackSide(
                    code = code,
                    modifier = modifier,
                )
            }
        }
    }
}

@Composable
fun UserPlasticCardFrontSide(
    discount: String,
    balance: String,
    onQrCodeClick: () -> Unit,
    modifier: Modifier = Modifier,
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
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold,
                    ),
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
                    .size(25.dp)
                    .clip(CircleShape)
                    .clickable { /* TODO */ }
            )
        }
        Row(verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.profile_card_balance),
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
                onClick = onQrCodeClick,
                modifier = Modifier.height(35.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.profile_card_qr_code),
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
fun UserPlasticCardBackSide(
    code: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = stringResource(id = R.string.profile_card_qr_code),
            modifier = modifier
                .weight(1f)
                .padding(top = 20.dp)
                .fillMaxSize()
        )
        Text(
            text = code,
            modifier = modifier
                .padding(10.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserPlasticCardPreview() {
    UserPlasticCard(discount = "25%", balance = "150", code = "123456789")
}
