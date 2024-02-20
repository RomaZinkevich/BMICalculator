package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    var weightText = remember { mutableStateOf("") }
    var heightText = remember { mutableStateOf("") }
    var bmi = remember { mutableStateOf(0.0) }
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Cyan)){
        Text(text = "BMI Calculator",
            fontSize = 60.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center)
        OutlinedTextField(
            value = weightText.value,
            onValueChange = { weightText.value = it },
            label = { Text(text = "Your Weight:") },
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number ),
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = heightText.value,
            onValueChange = { heightText.value = it },
            label = { Text(text = "Your Height:") },
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number ),
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text="Your BMI:",
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Text(text="${String.format("%.1f", bmi.value)}",
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            val weight = weightText.value.toDoubleOrNull()
            val height = heightText.value.toDoubleOrNull()
            if (weight != null && height != null ){
                bmi.value = weight / (height/100 * height/100)
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally))
        {
            Text(
                text = "Calculate BMI",
                fontSize = 20.sp,
                )
        }
    }
}
