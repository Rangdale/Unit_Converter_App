package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { it
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    Surface(color = Color.hsl(270f, 1f, 0.9f)) {
        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        var inputUnit by remember { mutableStateOf("Meters") }
        var outputUnit by remember { mutableStateOf("Meters") }
        var iExpanded by remember { mutableStateOf(false) }
        var oExpanded by remember { mutableStateOf(false) }
        val conversionFactor = remember { mutableStateOf(1.00) }
        val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Unit Converter",
                fontFamily = FontFamily.Cursive,
                style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                }                ,
                label = { Text("Enter Value") })
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                //Column is parent to Row
                //Row is parent to Box
                //Box = Parent of button

                //Input Box
                Box {
                    Button(onClick = { iExpanded = true }) {
                        Text(inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Meters"
                                conversionFactor.value = 1.00
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.3048
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Inches") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Inches"
                                conversionFactor.value = 0.0254
                                convertUnits()
                            })
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))

                //Output Box
                Box {
                    Button(onClick = { oExpanded = true }) {
                        Text(outputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeters"
                                oConversionFactor.value = 0.01
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Meters"
                                oConversionFactor.value = 1.00
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Feet"
                                oConversionFactor.value = 0.3048
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Inches") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Inches"
                                oConversionFactor.value = 0.0254
                                convertUnits()
                            })
                    }
                }
            }

            Text("Result: $outputValue $outputUnit",
                modifier = Modifier.padding(16.dp),
                fontFamily = FontFamily.Cursive,
                style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}