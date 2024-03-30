package com.example.fabrikatepla

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CodeViewModel: ViewModel() {
    val stateCode: MutableState<Boolean> = mutableStateOf(false)
}