package com.example.composeinstagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composeinstagramclone.ui.main.MainScreen
import com.example.composeinstagramclone.ui.theme.ComposeInstagramCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInstagramCloneTheme {
                MainScreen()
            }
        }
    }
}