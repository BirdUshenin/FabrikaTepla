package com.example.fabrikatepla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.example.fabrikatepla.domain.PermissionSMS
import com.example.fabrikatepla.presentation.ui.MainActivity
import com.example.fabrikatepla.ui.theme.otpCode

class AuthorizationActivity : ComponentActivity() {

    private val codeViewModel by viewModels<CodeViewModel>()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            PermissionSMS()

            window.statusBarColor = Color(0xFFFA6C37).toArgb()
            val showSuccessDialog = remember { mutableStateOf(false) }
            val isVerificationSuccess = remember { mutableStateOf(false) }

            Box(modifier = Modifier.fillMaxSize()) {

                EnteringCode(window,
                    codeViewModel = codeViewModel,
                    onVerifyClick = { codeTxt ->
                    isVerificationSuccess.value = otpCode.code == codeTxt
                    showSuccessDialog.value = true
                })

                codeViewModel.stateCode.value = showSuccessDialog.value
            }

            if (isVerificationSuccess.value) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
