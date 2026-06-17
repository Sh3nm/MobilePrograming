package com.example.gymmembershipapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymmembershipapp.FitTrackApplication
import com.example.gymmembershipapp.ui.screens.addmember.AddMemberViewModel
import com.example.gymmembershipapp.ui.screens.auth.LoginViewModel
import com.example.gymmembershipapp.ui.screens.auth.RegisterViewModel
import com.example.gymmembershipapp.ui.screens.home.HomeViewModel
import com.example.gymmembershipapp.ui.screens.membercard.MemberCardViewModel
import com.example.gymmembershipapp.ui.screens.reward.RewardViewModel
import com.example.gymmembershipapp.ui.screens.workout.WorkoutViewModel

/** Factories that wire each ViewModel to repositories from [FitTrackApplication]. */
object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            LoginViewModel(app().authRepository, app().sessionManager)
        }
        initializer {
            RegisterViewModel(app().authRepository, app().sessionManager)
        }
        initializer {
            HomeViewModel(app().memberRepository)
        }
        initializer {
            AddMemberViewModel(app().memberRepository)
        }
        initializer {
            MemberCardViewModel(
                memberId = requireMemberId(),
                memberRepository = app().memberRepository,
                workoutRepository = app().workoutRepository
            )
        }
        initializer {
            WorkoutViewModel(
                memberId = requireMemberId(),
                memberRepository = app().memberRepository,
                workoutRepository = app().workoutRepository
            )
        }
        initializer {
            RewardViewModel(
                memberId = requireMemberId(),
                memberRepository = app().memberRepository
            )
        }
    }
}

private fun CreationExtras.app(): FitTrackApplication =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FitTrackApplication

/** Reads the "memberId" nav argument (defined as IntType) from SavedStateHandle. */
private fun CreationExtras.requireMemberId(): Int {
    val handle = createSavedStateHandle()
    return handle.get<Int>("memberId")
        ?: handle.get<String>("memberId")?.toIntOrNull()
        ?: error("memberId argument is required")
}
