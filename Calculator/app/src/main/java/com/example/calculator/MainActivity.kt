package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("0") }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Angka pertama") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Angka kedua") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Hasil: $result")

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { result = hitung(num1, num2, "+") }) { Text("+") }
            Button(onClick = { result = hitung(num1, num2, "-") }) { Text("-") }
            Button(onClick = { result = hitung(num1, num2, "*") }) { Text("*") }
            Button(onClick = { result = hitung(num1, num2, "/") }) { Text("/") }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            num1 = ""
            num2 = ""
            result = "0"
        }) {
            Text("Clear")
        }
    }
}

fun hitung(n1: String, n2: String, op: String): String {
    val num1 = n1.toDoubleOrNull()
    val num2 = n2.toDoubleOrNull()

    if (num1 == null || num2 == null) {
        return "Input salah"
    }

    return when (op) {
        "+" -> (num1 + num2).toString()
        "-" -> (num1 - num2).toString()
        "*" -> (num1 * num2).toString()
        "/" -> {
            if (num2 != 0.0) (num1 / num2).toString()
            else "Error: bagi 0"
        }
        else -> "Operator salah"
    }
}