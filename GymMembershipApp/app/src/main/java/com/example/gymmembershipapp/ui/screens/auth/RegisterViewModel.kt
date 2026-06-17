package com.example.gymmembershipapp.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembershipapp.data.repository.AuthRepository
import com.example.gymmembershipapp.data.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmError: String? = null,
    val formError: String? = null,
    val isLoading: Boolean = false,
    val registerSuccess: Boolean = false
)

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) = _uiState.update { it.copy(name = value, nameError = null) }
    fun onEmailChange(value: String) = _uiState.update { it.copy(email = value, emailError = null, formError = null) }
    fun onPasswordChange(value: String) = _uiState.update { it.copy(password = value, passwordError = null) }
    fun onConfirmChange(value: String) = _uiState.update { it.copy(confirmPassword = value, confirmError = null) }

    fun register() {
        val state = _uiState.value
        val nameError = when {
            state.name.isBlank() -> "Nama tidak boleh kosong"
            state.name.trim().length < 3 -> "Nama minimal 3 karakter"
            else -> null
        }
        val emailError = when {
            state.email.isBlank() -> "Email tidak boleh kosong"
            !(state.email.contains("@") && state.email.contains(".")) -> "Format email tidak valid"
            else -> null
        }
        val passwordError = when {
            state.password.isBlank() -> "Password tidak boleh kosong"
            state.password.length < 6 -> "Password minimal 6 karakter"
            else -> null
        }
        val confirmError = when {
            state.confirmPassword.isBlank() -> "Konfirmasi password tidak boleh kosong"
            state.confirmPassword != state.password -> "Password tidak cocok"
            else -> null
        }

        if (nameError != null || emailError != null || passwordError != null || confirmError != null) {
            _uiState.update {
                it.copy(
                    nameError = nameError,
                    emailError = emailError,
                    passwordError = passwordError,
                    confirmError = confirmError
                )
            }
            return
        }

        _uiState.update { it.copy(isLoading = true, formError = null) }
        viewModelScope.launch {
            val account = authRepository.register(state.name, state.email, state.password)
            if (account != null) {
                sessionManager.saveSession(account.id, account.name, account.email)
                _uiState.update { it.copy(isLoading = false, registerSuccess = true) }
            } else {
                _uiState.update {
                    it.copy(isLoading = false, emailError = "Email sudah terdaftar")
                }
            }
        }
    }
}
