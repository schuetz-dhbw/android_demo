package de.dhbw.heidenheim.schuetz.simplenavigation.ui

import android.R.attr.name
import android.R.id.message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.util.CoilUtils.result
import dagger.hilt.android.lifecycle.HiltViewModel
import de.dhbw.heidenheim.schuetz.simplenavigation.data.repository.UserProfile
import de.dhbw.heidenheim.schuetz.simplenavigation.data.repository.UserProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserProfileRepository
) : ViewModel() {
    val profileState: StateFlow<ProfileUiState> = repository.getUserProfile()
        .map { entity ->
            entity?.let {
                ProfileUiState(
                    name = it.name,
                    imageUrl = it.imageUrl,
                    bio = it.bio
                )
            } ?: ProfileUiState()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProfileUiState()
        )

    fun saveProfile(name: String, imageUrl: String, bio: String) {
        viewModelScope.launch {
            repository.saveUserProfile(name, imageUrl, bio)
        }
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadRandomUser() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = repository.loadRandomUser()

            result
                .onSuccess { userProfile ->
                    saveProfile(userProfile.name, userProfile.imageUrl, userProfile.bio)
            }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Unknown Error"
                }

            _isLoading.value = false
        }
    }
}

data class ProfileUiState(
    val name: String = "",
    val imageUrl: String = "",
    val bio: String = ""
)