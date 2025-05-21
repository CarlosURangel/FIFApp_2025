package com.example.fifapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.fifapp.ui.presentation.LoginScreen
import com.example.fifapp.ui.presentation.SignUpScreen
import com.example.fifapp.ui.presentation.SettingsScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth) {

    NavHost(navController = navHostController, startDestination = "login"){
        composable("login") {
            LoginScreen(
                navigateToSignUp = { navHostController.navigate("signup") },
                navigateToHome = {
                    navHostController.navigate("home") {
                        // Limpiamos el back stack para que no se pueda volver al login
                        popUpTo("login") { inclusive = true }
                    }
                },
                auth = auth
            )
        }

        composable("signup") {
            SignUpScreen(
                navigateToLogin = {
                    navHostController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                auth = auth,
                onSignUpSuccess = {
                    navHostController.navigate("home") {
                        popUpTo("signup") { inclusive = true }
                    }
                }

            )
        }

        composable("home") {
            SettingsScreen(
                onLogout = {
                    auth.signOut()
                    navHostController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}

