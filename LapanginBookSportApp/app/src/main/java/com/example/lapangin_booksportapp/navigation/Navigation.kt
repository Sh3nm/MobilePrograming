package com.example.lapangin_booksportapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lapangin_booksportapp.ui.screens.HomeScreen
import com.example.lapangin_booksportapp.ui.screens.LoginScreen
import com.example.lapangin_booksportapp.ui.screens.LogoScreen
import com.example.lapangin_booksportapp.ui.screens.RegisterScreen
import com.example.lapangin_booksportapp.ui.screens.VenueDetailScreen
import com.example.lapangin_booksportapp.ui.screens.BookingFormScreen
import com.example.lapangin_booksportapp.ui.screens.BookingConfirmationScreen
import com.example.lapangin_booksportapp.ui.screens.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Logo : Screen("logo")
    object Register : Screen("register")
    object Login : Screen("login")
    object Home : Screen("home")
    object VenueDetail : Screen("venue_detail")
    object BookingForm : Screen("booking_form")
    object BookingConfirmation : Screen("booking_confirmation")
}

@Composable
fun BookSportNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Logo.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Logo.route) {
            LogoScreen(
                onGetStarted = {
                    navController.navigate(Screen.Register.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Logo.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Logo.route)
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Logo.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Logo.route)
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onVenueClick = {
                    navController.navigate(Screen.VenueDetail.route)
                }
            )
        }

        composable(Screen.VenueDetail.route) {
            VenueDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onBookClick = {
                    navController.navigate(Screen.BookingForm.route)
                }
            )
        }

        composable(Screen.BookingForm.route) {
            BookingFormScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSubmitClick = {
                    navController.navigate(Screen.BookingConfirmation.route)
                }
            )
        }

        composable(Screen.BookingConfirmation.route) {
            BookingConfirmationScreen(
                onBackToHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
