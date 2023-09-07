package com.example.composeinstagramclone.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.composeinstagramclone.ui.profiles.tabs.AllTabScreen
import com.example.composeinstagramclone.ui.profiles.tabs.ReelsTabScreen
import com.example.composeinstagramclone.ui.profiles.tabs.TagTabScreen
import com.example.composeinstagramclone.utils.navigation.ProfileTabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerView(
    rowList: List<String>,
    columnWidth: Dp,
    pages: List<ProfileTabItem>,
    pagerState: PagerState
){
    HorizontalPager(
        count = pages.size,
        state = pagerState
    ) { page ->
        when(page) {
            0 -> AllTabScreen(
//                rowList = rowList,
                columnWidth = columnWidth
            )
            1 -> ReelsTabScreen(
//                rowList = rowList,
                columnWidth = columnWidth
            )
            2 -> TagTabScreen(
//                rowList = rowList,
                columnWidth = columnWidth
            )
        }
//        Column() {
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//            Text(text = page.toString())
//        }
    }
}