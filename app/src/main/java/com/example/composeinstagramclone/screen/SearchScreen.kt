package com.example.composeinstagramclone.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth(0.95f),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray.copy(0.2f)
                        )
                    ) {
                        Icon(Icons.Outlined.Search, contentDescription = null, tint = Color.Black)
                        Text(text = "검색", color = Color.Gray, fontWeight = FontWeight.Bold)
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPading ->

    }
}

@Preview
@Composable
fun DefaultPreviewSearchScreen() {
    SearchScreen()
}