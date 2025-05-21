package com.example.fifapp.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fifapp.ui.components.Topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val primaryColor = Color(0xFF001F3F)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen() {
    Scaffold(
        topBar = { Topbar() }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        // Forma correcta de agregar items individuales
                        item { UserProfileSection() }
                        item { ScheduleOptions() }
                        item { WeeklyScheduleHeader() }
                        item {
                            ClassCard(
                                className = "Matemáticas Avanzadas",
                                schedule = "Lunes, 9:00 AM - 11:00 AM",
                                location = "Aula 301"
                            )
                        }
                        item {
                            ClassCard(
                                className = "Estructuras de Datos",
                                schedule = "Martes, 2:00 PM - 4:00 PM",
                                location = "Laboratorio 204"
                            )
                        }
                        item { ProfessorProfileSection() }
                    }
                }
            }

@Composable
fun UserProfileSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono de usuario en lugar de foto
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFD1C4E9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                contentDescription = "Usuario",
                modifier = Modifier.size(32.dp),
                tint = Color(0xFF001F3F)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "Bienvenido de vuelta",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Juan Pérez",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ScheduleOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Primera Card con texto debajo
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Card(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = primaryColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { /* Navegar a horario de clases */ }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Horario de clases",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Horario de clases",
                color = Color.Black, // Cambia el color según tu tema
                fontSize = 12.sp
            )
        }

        // Segunda Card con texto debajo
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Card(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = primaryColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { /* Navegar a horario de oficina */ }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Horarios",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Oficina",
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun WeeklyScheduleHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Horario Semanal",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Ver Todo",
            color = Color(0xFF001F3F),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ClassCard(className: String, schedule: String, location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = className,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = schedule)
            Text(text = location)
        }
    }
}

@Composable
fun ProfessorProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Perfil del Profesor",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Dra. María González",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Departamento de Ciencias de la Computación",
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = "Oficina: Aula 405",
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = "maria.gonzalez@universidad.edu",
            modifier = Modifier.padding(top = 8.dp),
            color = Color(0xFF001F3F)
        )

        Text(
            text = "+1 (555) 987-6543",
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = "Horario de atención: Lun/Mié 2-4 PM",
            modifier = Modifier.padding(top = 8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun ScheduleScreenPreview() {
    MaterialTheme {
        ScheduleScreen()
    }
}