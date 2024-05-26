package com.example.fabrikatepla.presentation.ui.profile

import com.example.fabrikatepla.data.Profile

data class ProfileScreenState(
    val loading: Boolean = true,
    val profile: Profile = Profile.DEFAULT_VALUE,
    val cabinetMenu: List<CabinetMenuElement> = listOf(),
    val supportInfo: SupportInfo = SupportInfo(),
    val error: Boolean = false,
)
