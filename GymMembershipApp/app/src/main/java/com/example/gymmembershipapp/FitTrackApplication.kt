package com.example.gymmembershipapp

import android.app.Application
import com.example.gymmembershipapp.data.local.FitTrackDatabase
import com.example.gymmembershipapp.data.repository.AuthRepository
import com.example.gymmembershipapp.data.repository.MemberRepository
import com.example.gymmembershipapp.data.repository.WorkoutRepository
import com.example.gymmembershipapp.data.session.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Manual dependency container. Keeps the app free of DI frameworks while still
 * giving ViewModels access to repositories (Presentation -> ViewModel -> Repository -> Room).
 */
class FitTrackApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database: FitTrackDatabase by lazy {
        FitTrackDatabase.getInstance(this, applicationScope)
    }

    val memberRepository: MemberRepository by lazy {
        MemberRepository(database.memberDao())
    }

    val workoutRepository: WorkoutRepository by lazy {
        WorkoutRepository(database.workoutDao())
    }

    val authRepository: AuthRepository by lazy {
        AuthRepository(database.accountDao())
    }

    val sessionManager: SessionManager by lazy {
        SessionManager(this)
    }
}
