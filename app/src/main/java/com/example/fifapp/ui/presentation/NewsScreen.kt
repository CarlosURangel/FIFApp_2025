package com.example.fifapp.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fifapp.ui.components.Topbar

@Composable
fun NewsScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Topbar() // TopBar personalizado
        // Resto de tu contenido...
    }
}