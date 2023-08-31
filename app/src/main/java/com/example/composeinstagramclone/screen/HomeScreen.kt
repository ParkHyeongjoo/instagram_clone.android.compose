package com.example.composeinstagramclone.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeinstagramclone.R
import com.example.composeinstagramclone.data.model.Book
import com.example.composeinstagramclone.data.model.DEFAULT_BOOKS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(books: List<Book> = DEFAULT_BOOKS) {
    val context = LocalContext.current

    val columnListState = rememberLazyListState()
    val rowListState = rememberLazyListState()
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
                        Icon(
                            Icons.Outlined.FavoriteBorder,
                            contentDescription = "favorite",
                            modifier = Modifier
                                .size(28.dp)
                        )
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
                                .size(28.dp)
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
            state = columnListState,
            ) {
            item {
                LazyRow(
                    state = rowListState
                ) {
                    item { Story(myProfile = true, model = null) }
                    items(books.size) {
                        Story(myProfile = false, model = books[it])
                    }
                }
            }
            item { androidx.compose.material.Divider(color = Color.Gray.copy(0.1f)) }
            items(books.size) {
                Book(model = books[it])
            }
        }
    }
}

@Composable
fun Story(modifier: Modifier = Modifier, myProfile: Boolean, model: Book?) {
    Column(
        modifier = modifier
            .width(100.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Gray.copy(0.5f)
                        ),
                        shape = CircleShape
                    )
            )
            if(myProfile) {
                Icon(
                    Icons.Filled.AddCircle,
                    contentDescription = null,
                    tint = Color.Blue,
                    modifier = Modifier
                        .offset(x = 24.dp, y = 24.dp)
                        .size(28.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color.White,
                            )
                        }
                )
            }else {
                Box(
                    modifier = modifier
                        .size(85.dp)
                        .background(Color.Transparent)
                        .border(
                            border = BorderStroke(
                                width = 3.dp,
                                brush = Brush.sweepGradient(
                                    listOf(
                                        Color(0xFF833AB4),
                                        Color(0xFFFD1D1D),
                                        Color(0xFFFCB045),
                                        Color(0xFF833AB4),
                                        )
                                )
                            ),
                            shape = CircleShape
                        )
                )
            }

        }
        Text(
            text = model?.author ?: "내 스토리",
            fontSize = 12.sp,
            modifier = modifier
                .width(80.dp),
            maxLines = 1,
            textAlign = TextAlign.Center
        )
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