package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val mensaje = rememberCoroutineScope()

    val preferencia = LocalContext.current.getSharedPreferences("datos", Context.MODE_PRIVATE)
    var nombre by remember { mutableStateOf("") }

    val contexto = LocalContext.current as Activity

    Scaffold(
        topBar = { TopAppBar(title = { Text("Almacenamiento") }) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it }
            )

            Button(onClick = {
                preferencia.edit().putString("nombre", nombre).apply()
                mensaje.launch {
                    snackbarHostState.showSnackbar(message = "Se guardó correctamente")
                }
            }) {
                Text("Guardar Nombre")
            }

            Spacer(Modifier.height(10.dp))


            if (preferencia.getString("nombre", "") == "") {
                Text("No ingresaste tu Nombre")
            } else {
                Text("Hola: " + preferencia.getString("nombre", ""))
            }

            Spacer(Modifier.height(10.dp))


            Button(onClick = {
                preferencia.edit().remove("nombre").apply()
                mensaje.launch {
                    snackbarHostState.showSnackbar(message = "Se eliminó correctamente")
                }
            }) {
                Text("Eliminar")
            }

            Spacer(Modifier.height(10.dp))

            Button(onClick = {
                contexto.finish()
            }) {
                Text("Cerrar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}