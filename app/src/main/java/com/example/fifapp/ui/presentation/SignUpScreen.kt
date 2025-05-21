package com.example.fifapp.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseAuth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifapp.ui.theme.DarkGray
import com.example.fifapp.ui.theme.White

@Composable
fun SignUpScreen(
    navigateToLogin: () -> Unit,
    auth: FirebaseAuth,
    onSignUpSuccess: () -> Unit = {}
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título principal
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = "FIF App",
                color = Color(0xFF001F3F),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        // Tarjeta con campos de registro
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(16.dp),
            color = White,
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título secundario dentro de la tarjeta
                Text(
                    text = "Crear cuenta",
                    color = Color(0xFF001F3F),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 16.dp)
                )

                // Campo Email
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFF001F3F)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Contraseña
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFF001F3F)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Confirmar Contraseña
                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFF001F3F)
                    )
                )

                // Mostrar mensaje de error si existe
                errorMessage.value?.let { message ->
                    Text(
                        text = message,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de Registro
                Button(
                    onClick = {
                        if (email.value.isBlank() || password.value.isBlank() || confirmPassword.value.isBlank()) {
                            errorMessage.value = "Por favor completa todos los campos"
                            return@Button
                        }

                        if (password.value != confirmPassword.value) {
                            errorMessage.value = "Las contraseñas no coinciden"
                            return@Button
                        }

                        if (password.value.length < 6) {
                            errorMessage.value = "La contraseña debe tener al menos 6 caracteres"
                            return@Button
                        }

                        isLoading.value = true
                        errorMessage.value = null

                        auth.createUserWithEmailAndPassword(email.value, password.value)
                            .addOnCompleteListener { task ->
                                isLoading.value = false
                                if (task.isSuccessful) {
                                    onSignUpSuccess()
                                } else {
                                    errorMessage.value = "Error al registrar: ${task.exception?.message}"
                                }
                            }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = !isLoading.value,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001F3F))
                ) {
                    if (isLoading.value) {
                        CircularProgressIndicator(color = White, modifier = Modifier.size(24.dp))
                    } else {
                        Text("Registrarse", fontSize = 16.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Texto para redirigir a Login
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("¿Ya tienes una cuenta? ", color = DarkGray)
            TextButton(onClick = navigateToLogin) {
                Text("Inicia sesión", color = Color(0xFF001F3F), fontWeight = FontWeight.Bold)
            }
        }
    }
}