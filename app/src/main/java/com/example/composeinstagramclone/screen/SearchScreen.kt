package com.example.composeinstagramclone.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    SearchBox(navController = navController)
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPading ->

    }
}

@Composable
private fun SearchBox(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth(0.95f)
            .background(Color.Gray.copy(0.15f))
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {
                navController.navigate("searchDetail") {
                    launchSingleTop = true
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(26.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "검색",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreviewSearchScreen() {
    val navController = rememberNavController()
    SearchScreen(navController)
}