package com.example.fifapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fifapp.ui.theme.Azul1

@Composable
fun Navbar(navController: NavController) {
    val items = listOf(
        BottomNavItem("index", Icons.Default.Home, "Inicio"),
        BottomNavItem("news", Icons.Default.Campaign, "Noticias"),
        BottomNavItem("events", Icons.Default.CalendarMonth, "Eventos"),
        BottomNavItem("schedule", Icons.Default.Groups, "Agenda"),
        BottomNavItem("settings", Icons.Default.Settings, "Ajustes")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavbarItem(
                icon = item.icon,
                label = item.label,
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavbarItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tintColor = if (selected) Azul1 else Color.Gray.copy(alpha = 0.5f)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clickable(
                onClick = onClick,
                indication = null, // Quitar ripple
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tintColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = tintColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)
