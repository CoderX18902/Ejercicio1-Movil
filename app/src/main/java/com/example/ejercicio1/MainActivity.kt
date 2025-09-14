package com.example.ejercicio1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio1.ui.theme.Ejercicio1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegistroRapido(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RegistroRapido(modifier: Modifier = Modifier) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var suscrito by rememberSaveable { mutableStateOf(false) }
    var resultado by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro rápido",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF6200EE)
        )

        Spacer(Modifier.height(16.dp))


        LabelAndField(
            label = "Nombre",
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = "Ingresa tu nombre"
        )

        Spacer(Modifier.height(8.dp))

        LabelAndField(
            label = "Correo",
            value = correo,
            onValueChange = { correo = it },
            placeholder = "ejemplo@correo.com",
            keyboardType = KeyboardType.Email
        )

        Spacer(Modifier.height(8.dp))


        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = suscrito, onCheckedChange = { suscrito = it })
            Text("Suscribirme al boletín")
        }

        Spacer(Modifier.height(16.dp))


        Button(
            onClick = {
                resultado = if (nombre.isNotBlank() && correo.contains("@")) {
                    "Registro exitoso para $nombre (${if (suscrito) "Suscrito" else "No suscrito"})"
                } else {
                    "Error: Ingresa un nombre y un correo válido"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
        ) {
            Text("Registrar")
        }

        Spacer(Modifier.height(16.dp))


        Text(
            text = "RESULTADO\n$resultado",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDE7F6), shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            color = Color.Black
        )
    }
}

@Composable
fun LabelAndField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            isError = isError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroRapidoPreview() {
    Ejercicio1Theme {
        RegistroRapido()
    }
}

