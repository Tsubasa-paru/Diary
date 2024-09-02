package com.example.anonymousdiaryapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anonymousdiaryapp.data.model.DiaryEntry
import com.example.anonymousdiaryapp.presentation.viewmodel.DiaryViewModel
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment

@Composable
fun DiaryScreen(
    onNavigateToSettings: () -> Unit,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    val entries by viewModel.entries.collectAsState()
    val feedback by viewModel.feedback.collectAsState()
    var selectedEntryId by remember { mutableStateOf<String?>(null) }
    var newEntryContent by remember { mutableStateOf("") }
    var newEntryMood by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("日記", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = onNavigateToSettings) {
                Text("設定")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 新しいエントリー入力フォーム
        OutlinedTextField(
            value = newEntryContent,
            onValueChange = { newEntryContent = it },
            label = { Text("日記の内容") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newEntryMood,
            onValueChange = { newEntryMood = it },
            label = { Text("気分") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                viewModel.addEntry(newEntryContent, newEntryMood)
                newEntryContent = ""
                newEntryMood = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("追加")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Gemini出力ボタン
        Button(
            onClick = {
                selectedEntryId?.let { viewModel.generateFeedback(it) }
            },
            enabled = selectedEntryId != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Geminiフィードバックを生成")
        }

        // フィードバック表示
        feedback?.let { feedbackContent ->
            Spacer(modifier = Modifier.height(16.dp))
            Text("フィードバック:", style = MaterialTheme.typography.titleMedium)
            Text(feedbackContent)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 日記エントリーリスト
        LazyColumn {
            items(entries) { entry ->
                DiaryEntryItem(
                    entry = entry,
                    onDelete = { viewModel.deleteEntry(it) },
                    onSelect = { selectedEntryId = it.id }
                )
            }
        }


    }
}

@Composable
fun DiaryEntryItem(
    entry: DiaryEntry,
    onDelete: (DiaryEntry) -> Unit,
    onSelect: (DiaryEntry) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onSelect(entry) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.content, style = MaterialTheme.typography.bodyLarge)
            Text(text = "気分: ${entry.mood}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "作成日時: ${entry.createdAt}", style = MaterialTheme.typography.bodySmall)
            TextButton(onClick = { onDelete(entry) }) {
                Text("削除")
            }
        }
    }
}