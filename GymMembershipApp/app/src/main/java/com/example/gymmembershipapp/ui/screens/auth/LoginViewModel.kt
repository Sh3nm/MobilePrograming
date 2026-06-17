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

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val formError: String? = null,
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false
)

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) =
        _uiState.update { it.copy(email = value, emailError = null, formError = null) }

    fun onPasswordChange(value: String) =
        _uiState.update { it.copy(password = value, passwordError = null, formError = null) }

    fun login() {
        val state = _uiState.value
        val emailError = when {
            state.email.isBlank() -> "Email tidak boleh kosong"
            !(state.email.contains("@") && state.email.contains(".")) -> "Format email tidak valid"
            else -> null
        }
        val passwordError = if (state.password.isBlank()) "Password tidak boleh kosong" else null

        if (emailError != null || passwordError != null) {
            _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
            return
        }

        _uiState.update { it.copy(isLoading = true, formError = null) }
        viewModelScope.launch {
            val account = authRepository.login(state.email, state.password)
            if (account != null) {
                sessionManager.saveSession(account.id, account.name, account.email)
                _uiState.update { it.copy(isLoading = false, loginSuccess = true) }
            } else {
                _uiState.update {
                    it.copy(isLoading = false, formError = "Email atau password salah")
                }
            }
        }
    }
}
