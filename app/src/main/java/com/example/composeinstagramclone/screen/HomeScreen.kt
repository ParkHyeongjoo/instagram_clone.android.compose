package com.example.composeinstagramclone.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeinstagramclone.R
import com.example.composeinstagramclone.data.model.Book
import com.example.composeinstagramclone.data.model.DEFAULT_BOOKS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(books: List<Book> = DEFAULT_BOOKS) {
    val context = LocalContext.current

    val listState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Button(
                        onClick = {
                                  /*TODO*/
                                  Toast.makeText(context, "logo click", Toast.LENGTH_SHORT).show()
                                  },
                        elevation = null,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .height(64.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.instagram_logo),
                                contentDescription = "logo",
                                contentScale = ContentScale.Fit,
                            )
                            Icon(Icons.Filled.ExpandMore, contentDescription = "")
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            /*TODO*/
                            Toast.makeText(context, "favorite click", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(Icons.Outlined.FavoriteBorder, contentDescription = "favorite")
                    }
                    IconButton(
                        onClick = {
                        /*TODO*/
                        Toast.makeText(context, "DM click", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            Icons.Outlined.Send,
                            contentDescription = "DM",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            state = listState,
            ) {
            item {  }
            items(books.size) {
                Book(model = books[it])
            }
        }
    }
}

@Composable
fun Book(modifier: Modifier = Modifier, model: Book) {
    androidx.compose.material.Card(
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = model.title)
            Text(text = model.author)
            androidx.compose.material.Text(text = "${model.pageCount} pages")
        }
    }
}

@Preview
@Composable
fun DefaultPreviewHomeScreen() {
    HomeScreen()
}