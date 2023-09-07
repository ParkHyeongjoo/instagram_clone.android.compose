package com.example.composeinstagramclone.ui.profiles.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun AllTabScreen(
//    rowList: List<String>,
    columnWidth: Dp,
) {
    LazyRow {
        items(100) { index ->
//            val item = rowList[index]
            Text(
                text = index.toString(),
                modifier = androidx.compose.ui.Modifier
                    .width(columnWidth)
                    .aspectRatio(1f) // 정사각형 grid item을 위한 ratio 지정
                    .border(width = Dp(1f), color = Color.Gray)
                    .background(Color.White)
            )
        }
    }
}