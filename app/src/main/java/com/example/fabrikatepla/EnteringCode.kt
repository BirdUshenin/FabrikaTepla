package com.example.fabrikatepla

import android.view.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

@Composable
fun EnteringCode(window: Window, onVerifyClick:(String)->Unit) {

    val codeTxtFieldTxt = remember { mutableStateOf("") }
    val textFieldRequester = remember { FocusRequester() }
    WindowCompat.setDecorFitsSystemWindows(window,false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Spacer(Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .height(330.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 33.dp, topEnd = 33.dp))
                .background(Color.White)
                .padding(end = 18.dp, start = 18.dp, top = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Введите новый код",
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(18.dp))

            NumberBox(
                codeText = codeTxtFieldTxt,
            ) {
                focusManager.clearFocus()
                if (textFieldRequester.requestFocus().equals(true)) {
                    keyboardController?.show()
                }
            }

//            Spacer(Modifier.height(12.dp))


            TextField(
                value = codeTxtFieldTxt.value,
                onValueChange = {
                    if (it.length <= 4) {
                        codeTxtFieldTxt.value = it
                        if (it.length == 4) {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    } else {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(0.dp)
                    .focusRequester(textFieldRequester)
                    .alpha(0f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                )
            )
            BottomActionButtons { onVerifyClick(codeTxtFieldTxt.value) }
        }

    }
}

@Composable
private fun BottomText() {
    buildAnnotatedString {
        append("Не получил код? ")
        this.addStyle(
            SpanStyle(color = Color.LightGray, fontFamily = FontFamily.SansSerif),
            0,
            17
        )
        append("Повторная отправка кода")
        this.addStyle(
            SpanStyle(color = Color.Blue, fontFamily = FontFamily.SansSerif),
            18,
            28
        )
    }
}

@Composable
fun NumberBox(
    codeText: MutableState<String>,
    onOtpFieldClick: () -> Unit,
) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                onOtpFieldClick()
            }) {
        TextFieldBox(
            text = if (codeText.value.isNotEmpty()) codeText.value[0].toString() else ""
        )

        TextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 2) codeText.value[1].toString() else ""
        )

        TextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 3) codeText.value[2].toString() else ""
        )

        TextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 4) codeText.value[3].toString() else ""
        )
    }
}

@Composable
private fun TextFieldBox(text: String) {

    Box(
        modifier = Modifier
            .width(63.dp)
            .height(72.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xfff7f7f7)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}


@Composable
private fun ColumnScope.BottomActionButtons(onVerifyClick: () -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .weight(1f),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color(0xFF9300FC),
                disabledBackgroundColor = Color(0x009300FC)
            ),
            enabled = true,
            modifier = Modifier
                .weight(1f)
                .border(2.5.dp, Color(0xFF9500FF), shape = RoundedCornerShape(100.dp)),
            shape = RoundedCornerShape(100.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Cancel ?"
            )
            Spacer(Modifier.width(6.dp))
            androidx.compose.material.Text(
                "Cancel",
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 6.dp, top = 6.dp, bottom = 6.dp)
            )

        }

        Spacer(Modifier.width(6.dp))

        Button(
            onClick = {
                onVerifyClick()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF9500FF),
                contentColor = Color.White,
                disabledBackgroundColor = Color(0x7A9300FC)
            ),
            enabled = true,
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.weight(1f),

            ) {

            androidx.compose.material.Text(
                "Verify",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            )

        }


    }


}