package com.example.fifapp.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.SwitchDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fifapp.ui.components.Topbar

// Color personalizado: Azul oscuro (0xFF001F3F)
val customPrimaryColor = Color(0xFF001F3F)

@Composable
fun SettingsScreen(onLogout: () -> Unit) {
    Scaffold(
        topBar = { Topbar() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // --- Sección de Información ---
                Text(
                    text = "Información",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = customPrimaryColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Juan Eliseo Gutiérrez Camarena",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "juan@gmail.com",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Divider(modifier = Modifier.padding(bottom = 16.dp))

                // --- Sección de Preferencias ---
                Text(
                    text = "Preferencias",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = customPrimaryColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Modo Oscuro (con Switch)
                val darkModeEnabled = remember { mutableStateOf(false) }
                PreferenceItem(
                    icon = Icons.Default.DarkMode,
                    title = "Modo oscuro",
                    action = {
                        Switch(
                            checked = darkModeEnabled.value,
                            onCheckedChange = { darkModeEnabled.value = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = customPrimaryColor,
                                checkedTrackColor = customPrimaryColor.copy(alpha = 0.4f),
                            )
                        )
                    }
                )

                // Notificaciones (con Switch)
                val notificationsEnabled = remember { mutableStateOf(true) }
                PreferenceItem(
                    icon = Icons.Default.Notifications,
                    title = "Notificaciones",
                    action = {
                        Switch(
                            checked = notificationsEnabled.value,
                            onCheckedChange = { notificationsEnabled.value = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = customPrimaryColor,
                                checkedTrackColor = customPrimaryColor.copy(alpha = 0.4f),
                            )
                        )
                    }
                )

                // Acerca de
                PreferenceItem(
                    icon = Icons.Default.Info,
                    title = "Acerca de",
                    action = { /* Navegar a pantalla de Acerca de */ }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón de Cerrar Sesión
                Button(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customPrimaryColor
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Cerrar sesión",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Cerrar sesión")
                }
            }
        }
    )
}

// Añade esto en tu archivo SettingsScreen.kt (antes o después de SettingsScreen)
@Composable
fun PreferenceItem(
    icon: ImageVector,  // Cambiado a ImageVector
    title: String,
    action: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = title, fontSize = 16.sp)
        }
        action()
    }
}