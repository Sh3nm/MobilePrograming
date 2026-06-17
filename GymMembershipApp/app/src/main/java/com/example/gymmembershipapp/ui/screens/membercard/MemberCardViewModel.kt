package com.example.gymmembershipapp.ui.screens.membercard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import com.example.gymmembershipapp.data.local.entity.WorkoutEntity
import com.example.gymmembershipapp.data.repository.MemberRepository
import com.example.gymmembershipapp.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class MemberCardUiState(
    val member: MemberEntity? = null,
    val workouts: List<WorkoutEntity> = emptyList(),
    val isLoading: Boolean = true
)

class MemberCardViewModel(
    memberId: Int,
    memberRepository: MemberRepository,
    workoutRepository: WorkoutRepository
) : ViewModel() {

    val uiState: StateFlow<MemberCardUiState> =
        combine(
            memberRepository.getMemberById(memberId),
            workoutRepository.getWorkoutsByMember(memberId)
        ) { member, workouts ->
            MemberCardUiState(member = member, workouts = workouts, isLoading = false)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MemberCardUiState()
        )
}
