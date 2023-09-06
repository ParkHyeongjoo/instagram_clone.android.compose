package com.example.composeinstagramclone.ui.profiles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeinstagramclone.ui.common.PagerView
import com.example.composeinstagramclone.utils.navigation.ProfileTabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ProfileScreen() {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 10.dp, end = 8.dp)
                                .clickable { /*TODO*/ }
                        ) {
                            Text(
                                text = "j_park_bro",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clickable { /*TODO*/ }
                        ) {
                            Icon(
                                Icons.Outlined.ExpandMore,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(28.dp)
                            )
                        }
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
        Content(
            innerPadding = innerPadding,
            pagerState = pagerState,
            coroutineScope = coroutineScope
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    // content: List<String>,
    // columnSize: Int,
) {
    val columnSize: Int = 3
    val list = (1..30).map { it.toString() }
    val rowsCount = 1 + (list.size - 1) / columnSize

    BoxWithConstraints {
        val maxWidth = maxWidth
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
        ) {
            item { ProfileState() }
            stickyHeader { StickyTab(pagerState = pagerState, coroutineScope = coroutineScope) }
            items(rowsCount) { rowIndex ->
                val rangeStart = rowIndex * columnSize
                var rangeEnd = rangeStart + columnSize - 1
                if (rangeEnd > list.lastIndex) rangeEnd = list.lastIndex
                PagerView(
                    rowList = list.slice(rangeStart..rangeEnd),
                    columnWidth = maxWidth / columnSize,
                    pages = fragments,
                    pagerState = pagerState
                )
            }
        }
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

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun StickyTab(
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) {
    androidx.compose.material.TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .border(
                width = 1.dp,
                color = Color.Gray.copy(0.15f)
            ),
        backgroundColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier
                    .pagerTabIndicatorOffset(
                        pagerState = pagerState,
                        tabPositions = tabPositions
                    ),
                height = 2.dp,
                color = Color.Black
            )
        },
    ) {
        fragments.forEachIndexed { index, s ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                }
            ) {
                Box(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        s.icon,
                        contentDescription = s.resourceId,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }
        }

    }
}

private val fragments = listOf(
    ProfileTabItem.AllTabScreen,
    ProfileTabItem.ReelsTabScreen,
    ProfileTabItem.TagTabScreen,
)

@Preview
@Composable
fun DefaultPreviewProfileScreen() {
    ProfileScreen()
}