package com.salah.carnova.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salah.carnova.domain.CarModel
import com.salah.carnova.ui.auth.AuthScreen
import com.salah.carnova.ui.feature.intro.IntroScreen
import com.salah.carnova.ui.feature.intro.detail.DetailScreen
import com.salah.carnova.ui.feature.intro.home.HomeScreen
import com.salah.carnova.ui.feature.intro.profile.ProfileScreen
import com.salah.carnova.viewModel.CarViewModel
import com.salah.carnova.viewModel.CategoryViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val categoryViewModel: CategoryViewModel = viewModel()
    val carViewModel: CarViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.INTRO
    ) {
        // --- Écran d’introduction ---
        composable(Screens.INTRO) {
            IntroScreen(navToMain = { navController.navigate(Screens.LOGIN) })
        }

        // --- Écran de connexion (login) ---
        composable(Screens.LOGIN) {
            AuthScreen(
                onLoginSuccess = { navController.navigate(Screens.MAIN) },

            )
        }

        // --- Écran principal ---
        composable(Screens.MAIN) {
            HomeScreen(
                onProfileClick = { navController.navigate(Screens.PROFILE) },
                onCarClick = { car ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("car", car)
                    navController.navigate(Screens.DETAIL)
                },
                carViewModel = carViewModel,
                categoryViewModel = categoryViewModel
            )
        }

        // --- Profil ---
        composable(Screens.PROFILE) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }

        // --- Détail voiture ---
        composable(Screens.DETAIL) {
            val car = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<CarModel>("car")
            if (car != null) {
                DetailScreen(car = car, onBack = { navController.popBackStack() })
            }
        }
    }
}


object Screens {
    const val INTRO = "intro"
    const val LOGIN = "login"
    const val MAIN = "main"
    const val PROFILE = "profile"
    const val DETAIL = "details"
}
