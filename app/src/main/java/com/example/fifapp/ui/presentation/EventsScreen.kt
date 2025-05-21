package com.example.fifapp.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifapp.ui.components.Topbar

@Composable
fun EventsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Topbar() // TopBar personalizado
        // Resto de tu contenido...
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No hay eventos disponibles por ahora.",
            fontSize = 18.sp
        )
    }
}
