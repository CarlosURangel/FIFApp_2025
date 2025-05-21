package com.example.fifapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fifapp.ui.components.Navbar
import com.example.fifapp.ui.presentation.HomeScreen
import com.example.fifapp.ui.presentation.LoginScreen
import com.example.fifapp.ui.presentation.NewsScreen
import com.example.fifapp.ui.presentation.EventsScreen
import com.example.fifapp.ui.presentation.ScheduleScreen
import com.example.fifapp.ui.presentation.SettingsScreen
import com.example.fifapp.ui.presentation.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Route(navController: NavHostController, auth: FirebaseAuth) {
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(auth) {
        startDestination = if (auth.currentUser == null) "login" else "index"
    }

    if (startDestination == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        Scaffold(
            bottomBar = {
                if (currentDestination !in listOf("login", "signup")) {
                    Navbar(navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestination!!,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("login") {
                    LoginScreen(
                        auth = auth,
                        navigateToHome = {
                            navController.navigate("index") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        navigateToSignUp = {
                            navController.navigate("signup")
                        }
                    )
                }

                composable("signup") {
                    SignUpScreen(
                        auth = auth,
                        navigateToLogin = {
                            navController.navigate("login") {
                                popUpTo("signup") { inclusive = true }
                            }
                        }
                    )
                }

                composable("index") { HomeScreen() }
                composable("news") { NewsScreen() }
                composable("events") { EventsScreen() }
                composable("schedule") { ScheduleScreen() }

                composable("settings") {
                    SettingsScreen(
                        onLogout = {
                            auth.signOut()
                            navController.navigate("login") {
                                popUpTo("index") { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
