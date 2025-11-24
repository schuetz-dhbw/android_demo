package de.dhbw.heidenheim.schuetz.simplenavigation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.dhbw.heidenheim.schuetz.simplenavigation.data.repository.UserProfileRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
}

data class ProfileUiState(
    val name: String = "",
    val imageUrl: String = "",
    val bio: String = ""
)