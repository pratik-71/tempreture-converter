@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tempretureconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tempretureconverter.ui.theme.TempretureConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TempretureConverterTheme {
                TemperatureConverterApp()
            }
        }
    }
}

@Composable
fun TemperatureConverterApp() {
    var inputTemperature by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Handle conversion
    fun convertToCelsius() {
        val temp = inputTemperature.text.toDoubleOrNull()
        if (temp != null) {
            result = "${(temp - 32) * 5 / 9} °C"
            errorMessage = ""
        } else {
            errorMessage = "Please enter a valid number."
        }
    }

    fun convertToFahrenheit() {
        val temp = inputTemperature.text.toDoubleOrNull()
        if (temp != null) {
            result = "${(temp * 9 / 5) + 32} °F"
            errorMessage = ""
        } else {
            errorMessage = "Please enter a valid number."
        }
    }

    // Layout and UI elements
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Temperature Converter")
                },
                actions = {
                    // Any actions you want in the top bar
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = inputTemperature,
                    onValueChange = { inputTemperature = it },
                    label = { Text("Enter Temperature") },
                    placeholder = { Text("e.g. 100") },
                    isError = errorMessage.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { convertToCelsius() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Convert to Celsius")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { convertToFahrenheit() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Convert to Fahrenheit")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (result.isNotEmpty()) {
                    Text(
                        text = result,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TemperatureConverterAppPreview() {
    TempretureConverterTheme {
        TemperatureConverterApp()
    }
}
