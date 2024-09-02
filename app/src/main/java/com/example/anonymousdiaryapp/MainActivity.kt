package com.example.anonymousdiaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.anonymousdiaryapp.presentation.navigation.NavGraph
import com.example.anonymousdiaryapp.presentation.theme.AnonymousDiaryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnonymousDiaryAppTheme {
                NavGraph()
            }
        }
    }
}