package com.example.fabrikatepla.presentation.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fabrikatepla.R

@Composable
fun Menu(supportInfo: SupportInfo, modifier: Modifier = Modifier) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    IconButton(onClick = { showBottomSheet = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.profile_menu),
        )
    }
    if (showBottomSheet) {
        BottomSheet(
            supportInfo = supportInfo,
            onDismissRequest = {
                showBottomSheet = false
            },
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    supportInfo: SupportInfo,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxHeight(0.45f)
    ) {
        Column {
            Spacer(modifier = modifier.height(20.dp))
            Column(modifier = modifier) {
                Text(
                    text = stringResource(id = R.string.profile_bottom_sheet_any_question),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
                OutlinedCard(
                    onClick = { /* TODO */ },
                    shape = RectangleShape,
                    border = BorderStroke(0.dp, Color.Transparent),
                ) {
                    Text(
                        text = stringResource(id = R.string.profile_bottom_sheet_support),
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                Spacer(modifier = modifier.height(30.dp))
                Text(
                    text = stringResource(id = R.string.profile_bottom_sheet_support_24_7),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = supportInfo.supportNumber,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 30.dp, bottom = 25.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            OutlinedCard(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                border = BorderStroke(0.dp, Color.Transparent),
            ) {
                Row(
                    modifier = modifier.padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.profile_bottom_sheet_log_out),
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Icon(
                        painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.ExitToApp),
                        contentDescription = stringResource(
                            id = R.string.profile_bottom_sheet_log_out
                        )
                    )
                }
            }

            Text(
                text = stringResource(
                    id = R.string.profile_bottom_sheet_app_version,
                    supportInfo.appVersion
                ),
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
    }
}

