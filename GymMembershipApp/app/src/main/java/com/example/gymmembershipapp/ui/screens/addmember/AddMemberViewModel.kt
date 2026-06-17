package com.example.gymmembershipapp.ui.screens.addmember

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import com.example.gymmembershipapp.data.repository.MemberRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class AddMemberUiState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)

class AddMemberViewModel(private val memberRepository: MemberRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AddMemberUiState())
    val uiState: StateFlow<AddMemberUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) = _uiState.update { it.copy(name = value, nameError = null) }
    fun onEmailChange(value: String) = _uiState.update { it.copy(email = value, emailError = null) }
    fun onPhoneChange(value: String) = _uiState.update { it.copy(phone = value, phoneError = null) }

    fun saveMember() {
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
        val digits = state.phone.filter { it.isDigit() }
        val phoneError = when {
            state.phone.isBlank() -> "Nomor HP tidak boleh kosong"
            digits.length < 10 -> "Nomor HP minimal 10 digit"
            else -> null
        }

        if (nameError != null || emailError != null || phoneError != null) {
            _uiState.update {
                it.copy(nameError = nameError, emailError = emailError, phoneError = phoneError)
            }
            return
        }

        _uiState.update { it.copy(isSaving = true) }
        viewModelScope.launch {
            val memberId = "FT-${System.currentTimeMillis().toString().takeLast(7)}"
            val joinDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
            memberRepository.insertMember(
                MemberEntity(
                    name = state.name.trim(),
                    email = state.email.trim(),
                    phone = state.phone.trim(),
                    joinDate = joinDate,
                    points = 0,
                    memberId = memberId
                )
            )
            _uiState.update { it.copy(isSaving = false, saveSuccess = true) }
        }
    }
}
