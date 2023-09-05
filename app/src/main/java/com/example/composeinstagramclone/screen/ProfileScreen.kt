package com.example.composeinstagramclone.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    TextButton(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "j_park_bro", fontSize = 18.sp)
                        Icon(Icons.Outlined.ExpandMore, contentDescription = null, modifier = Modifier.size(28.dp))
                    }
                },
                title = {},
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Outlined.AddBox, contentDescription = null, modifier = Modifier.size(30.dp))
                    }
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Outlined.Menu, contentDescription = null, modifier = Modifier.size(30.dp))
                    }
                }
            )
        }
    ) { innerPadding ->
        Content(innerPadding = innerPadding)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
    val list = (1..10).map { it.toString() }

    LazyColumn(
        modifier = modifier
            .padding(innerPadding)
    ) {
        item { ProfileState() }
        stickyHeader { StickyHeader() }
//        item { GridContent() }
//        items(list.size) { index ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.Red
//                )
//            ) {
//                Text(
//                    text =  list[index],
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 30.sp,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//        }
    }
}

@Composable
private fun ProfileState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.Blue)
    )
}

@Composable
private fun StickyHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Yellow)
    )
}

@Composable
private fun GridContent() {
    val list = (1..10).map { it.toString() }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(list.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red
                )
            ) {
                Text(
                    text =  list[index],
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreviewProfileScreen() {
    ProfileScreen()
}