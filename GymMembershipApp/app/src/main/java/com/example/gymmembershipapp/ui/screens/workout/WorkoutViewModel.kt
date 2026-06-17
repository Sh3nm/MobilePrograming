package com.example.gymmembershipapp.ui.screens.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembershipapp.data.local.entity.WorkoutEntity
import com.example.gymmembershipapp.data.repository.MemberRepository
import com.example.gymmembershipapp.data.repository.WorkoutRepository
import com.example.gymmembershipapp.domain.WorkoutType
import com.example.gymmembershipapp.domain.calculatePoints
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class WorkoutUiState(
    val selectedType: WorkoutType = WorkoutType.CARDIO,
    val duration: Int = 30,
    val isSaving: Boolean = false,
    val savedPoints: Int? = null   // non-null once saved -> trigger navigation/snackbar
) {
    val estimatedPoints: Int get() = calculatePoints(duration)
}

class WorkoutViewModel(
    private val memberId: Int,
    private val memberRepository: MemberRepository,
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkoutUiState())
    val uiState: StateFlow<WorkoutUiState> = _uiState.asStateFlow()

    fun selectType(type: WorkoutType) = _uiState.update { it.copy(selectedType = type) }

    fun increaseDuration() = _uiState.update { it.copy(duration = it.duration + 5) }

    fun decreaseDuration() = _uiState.update {
        it.copy(duration = (it.duration - 5).coerceAtLeast(5))
    }

    fun saveWorkout() {
        if (_uiState.value.isSaving) return
        _uiState.update { it.copy(isSaving = true) }
        viewModelScope.launch {
            val state = _uiState.value
            val points = state.estimatedPoints
            val date = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())

            workoutRepository.insertWorkout(
                WorkoutEntity(
                    memberId = memberId,
                    workoutType = state.selectedType.label,
                    duration = state.duration,
                    pointEarned = points,
                    date = date
                )
            )

            // Add earned points to the member.
            val current = memberRepository.getMemberById(memberId).first()
            if (current != null) {
                memberRepository.updateMemberPoints(memberId, current.points + points)
            }

            _uiState.update { it.copy(isSaving = false, savedPoints = points) }
        }
    }
}
