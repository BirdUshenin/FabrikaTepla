package com.example.fabrikatepla.presentation.ui.profile

import com.example.fabrikatepla.data.Profile

data class ProfileScreenState(
    val loading: Boolean = true,
    val profile: Profile,
    val error: Boolean = false,
)
