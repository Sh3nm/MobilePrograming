package com.example.gymmembershipapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gymmembershipapp.FitTrackApplication
import com.example.gymmembershipapp.ui.screens.addmember.AddMemberScreen
import com.example.gymmembershipapp.ui.screens.auth.LoginScreen
import com.example.gymmembershipapp.ui.screens.auth.RegisterScreen
import com.example.gymmembershipapp.ui.screens.home.HomeScreen
import com.example.gymmembershipapp.ui.screens.membercard.MemberCardScreen
import com.example.gymmembershipapp.ui.screens.reward.RewardScreen
import com.example.gymmembershipapp.ui.screens.splash.SplashScreen
import com.example.gymmembershipapp.ui.screens.workout.WorkoutScreen

@androidx.compose.material3.ExperimentalMaterial3Api
@Composable
fun FitTrackNavigation() {
    val navController = rememberNavController()
    val sessionManager =
        (LocalContext.current.applicationContext as FitTrackApplication).sessionManager

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashScreen(
                onTimeout = {
                    val destination = if (sessionManager.isLoggedIn()) Routes.HOME else Routes.LOGIN
                    navController.navigate(destination) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateRegister = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onMemberClick = { id -> navController.navigate(Routes.memberCard(id)) },
                onAddMember = { navController.navigate(Routes.ADD_MEMBER) },
                onLogout = {
                    sessionManager.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.ADD_MEMBER) {
            AddMemberScreen(
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.MEMBER_CARD,
            arguments = listOf(navArgument(Routes.ARG_MEMBER_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val memberId = backStackEntry.arguments?.getInt(Routes.ARG_MEMBER_ID) ?: return@composable
            MemberCardScreen(
                onBack = { navController.popBackStack() },
                onWorkout = { navController.navigate(Routes.workout(memberId)) },
                onReward = { navController.navigate(Routes.reward(memberId)) }
            )
        }

        composable(
            route = Routes.WORKOUT,
            arguments = listOf(navArgument(Routes.ARG_MEMBER_ID) { type = NavType.IntType })
        ) {
            WorkoutScreen(
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.REWARD,
            arguments = listOf(navArgument(Routes.ARG_MEMBER_ID) { type = NavType.IntType })
        ) {
            RewardScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
