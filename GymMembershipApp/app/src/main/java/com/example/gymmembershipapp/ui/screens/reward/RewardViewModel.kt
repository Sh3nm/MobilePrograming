package com.example.gymmembershipapp.ui.screens.reward

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import com.example.gymmembershipapp.data.repository.MemberRepository
import com.example.gymmembershipapp.domain.Reward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RewardViewModel(
    private val memberId: Int,
    private val memberRepository: MemberRepository
) : ViewModel() {

    val member: StateFlow<MemberEntity?> =
        memberRepository.getMemberById(memberId)
            .map { it }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )

    /** One-shot message to show in a Snackbar after a redeem attempt. */
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    fun consumeMessage() {
        _message.value = null
    }

    fun redeem(reward: Reward) {
        viewModelScope.launch {
            val current = memberRepository.getMemberById(memberId).first() ?: return@launch
            if (current.points >= reward.pointsRequired) {
                memberRepository.updateMemberPoints(memberId, current.points - reward.pointsRequired)
                _message.value = "Reward \"${reward.name}\" berhasil ditukar! -${reward.pointsRequired} poin"
            } else {
                val need = reward.pointsRequired - current.points
                _message.value = "Poin tidak cukup. Butuh $need poin lagi."
            }
        }
    }
}
