package com.example.fabrikatepla.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabrikatepla.domain.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state: StateFlow<ProfileScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getProfileInfo()
        }
    }

    private suspend fun getProfileInfo() {
        _state.update {
            it.copy(loading = true)
        }
        try {
            val profileDeferred = viewModelScope.async {
                ApiClient.apiService.getProfiles()
            }
            val cabinetMenuDeferred = viewModelScope.async {
                getMyCabinetMenu()
            }
            val profile = profileDeferred.await()
            val cabinetMenu = cabinetMenuDeferred.await()
            _state.update {
                it.copy(
                    loading = false,
                    cabinetMenu = cabinetMenu,
                    profile = profile,
                )
            }
        } catch (e: Throwable) {
            _state.update {
                it.copy(error = true)
            }
        }

    }

    // This could be a network request
    private suspend fun getMyCabinetMenu(): List<CabinetMenuElement> {
        return listOf(
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
            CabinetMenuElement.ClickableElement(
                title = "Россия",
                image = "https://cdn-0.emojis.wiki/emoji-pics/twitter/russia-twitter.png",
            ),
        )
    }

}
