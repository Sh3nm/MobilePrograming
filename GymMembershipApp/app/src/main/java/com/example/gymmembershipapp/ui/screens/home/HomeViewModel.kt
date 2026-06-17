package com.example.gymmembershipapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import com.example.gymmembershipapp.data.repository.MemberRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val members: List<MemberEntity> = emptyList(),
    val isLoading: Boolean = true
)

class HomeViewModel(memberRepository: MemberRepository) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        memberRepository.getAllMembers()
            .map { HomeUiState(members = it, isLoading = false) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState()
            )
}
