package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class BienvenidaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                BienvenidaScreen()
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BienvenidaScreen() {
    val contexto = LocalContext.current as Activity
    val prefs = LocalContext.current.getSharedPreferences("datos", Context.MODE_PRIVATE)
    val usuario = prefs.getString("usuario", "")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Bienvenida") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido, $usuario",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Equipo:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Text(text = "Zavala Huacarpuma, Juan Aldair (100%)", fontSize = 16.sp)
            Text(text = "Aguilar Vargas, Fabrizio Yair (100%)", fontSize = 16.sp)
            Text(text = "Leon Quispe, Cristian Hemerson (100%)", fontSize = 16.sp)
            Text(text = "Rocca Ramos, Rebeca Yasbet (100%)", fontSize = 16.sp)

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    prefs.edit().clear().apply()
                    contexto.startActivity(Intent(contexto, LoginActivity::class.java))
                    contexto.finish()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE)
                )
            ) {
                Text(
                    text = "CERRAR SESIÓN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BienvenidaScreenPreview() {
    MyApplicationTheme {
        BienvenidaScreen()
    }
}
