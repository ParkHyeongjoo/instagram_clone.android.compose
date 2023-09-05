package com.example.composeinstagramclone.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDetailScreen(navController: NavController) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Filled.KeyboardBackspace,
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                },
                title = {
                    var textInput by remember { mutableStateOf("") }
                    val changeValueTextInput: (String) -> Unit = { textInput = it }
                    SearchBox(
                        value = textInput,
                        onValueChange = changeValueTextInput,
                        focusRequester = focusRequester,
                        focusManager = focusManager
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier
            .addFocusCleaner(focusManager)
    ) { innerPadding ->

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBox(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    focusManager: FocusManager
) {
    val singleLine = true
    val enabled = true
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        enabled = enabled,
        interactionSource = interactionSource,
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(0.05f)
            .focusRequester(focusRequester)
    ) {
        TextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = it,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            leadingIcon = {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = null,
                    tint = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .size(26.dp)
                )
            },
            placeholder = {
                Text(
                    text = "검색",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            },
            contentPadding = PaddingValues(0.dp),
            container = {
                Box(
                    modifier = modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color.Gray.copy(0.15f))
                )
            }
        )
    }
    LaunchedEffect("") {
        focusRequester.requestFocus()
    }
}

// 바탕화면 클릭시 포커스 제거
fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}

@Preview
@Composable
fun DefaultPreviewSearchDetailScreen() {
    val navController = rememberNavController()

    SearchDetailScreen(navController)
}