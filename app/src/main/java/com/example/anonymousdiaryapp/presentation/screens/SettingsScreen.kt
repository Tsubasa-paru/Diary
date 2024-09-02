package com.example.anonymousdiaryapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anonymousdiaryapp.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settings by viewModel.settings.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = settings.geminiApiKey,
            onValueChange = { viewModel.updateSettings(settings.copy(geminiApiKey = it)) },
            label = { Text("Gemini API Key") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Temperature: ${settings.temperature}")
        Slider(
            value = settings.temperature,
            onValueChange = { viewModel.updateSettings(settings.copy(temperature = it)) },
            valueRange = 0f..1f,
            steps = 10
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = settings.showSolutions,
                onCheckedChange = { viewModel.updateSettings(settings.copy(showSolutions = it)) }
            )
            Text("Show solutions")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateBack) {
            Text("Back")
        }
    }
}