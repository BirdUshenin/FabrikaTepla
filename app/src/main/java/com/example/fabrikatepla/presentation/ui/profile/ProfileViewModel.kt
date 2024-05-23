package com.example.fabrikatepla.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabrikatepla.data.Profile
import com.example.fabrikatepla.domain.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenState(true, Profile.DEFAULT_VALUE, true))
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
            val profile = ApiClient.apiService.getProfiles()
//            delay(1000) // simulated loading
            _state.update {
                it.copy(
                    loading = false,
                    profile = profile,
                )
            }
        } catch (e: Throwable) {
            _state.update {
                it.copy(error = true)
            }
        }

    }

}
