package com.example.scaffold

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.InputChip
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.library.CustomButtons
import com.sryang.library.bottomAppBarTypeList
import com.sryang.library.scaffoldTypeList
import com.sryang.library.themeTypeList
import com.sryang.topappbar.topAppBarTypeList
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScaffoldSelector() {
    var selectedScaffold by remember { mutableStateOf(scaffoldTypeList[0]) }
    val topPageState = rememberPagerState { topAppBarTypeList.size }
    val scaffoldPageState = rememberPagerState { scaffoldTypeList.size }
    val bottomPageState = rememberPagerState { bottomAppBarTypeList.size }
    val themePageState = rememberPagerState { themeTypeList.size }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var leftValue by remember { mutableFloatStateOf(0f) }
    var rightValue by remember { mutableFloatStateOf(0f) }


    LaunchedEffect(key1 = "") {
        snapshotFlow {
            scaffoldPageState.currentPage
        }.collect {
            selectedScaffold = scaffoldTypeList[it]
        }
    }

    ThemeSelector(themePageState) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            selectedScaffold.scaffold(
                selectTopBar(topPageState),
                selectBottomBar(bottomPageState),
                { SnackbarHost(hostState = snackbarHostState) },
                WindowInsets(left = leftValue.dp, right = rightValue.dp),
                { CustomButtons() }
            )
        }
    }

    Selector(topPageState, bottomPageState, scaffoldPageState, themePageState, onSnackBar = {
        scope.launch { snackbarHostState.showSnackbar("Snackbar") }
    }, onLeftValueChange = { leftValue = it },
        leftValue = leftValue,
        rightValue = rightValue,
        onRightValueChange = { rightValue = it }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Selector(
    topPageState: PagerState,
    bottomPageState: PagerState,
    scaffoldPageState: PagerState,
    themePageState: PagerState,
    onSnackBar: () -> Unit,
    leftValue: Float,
    onLeftValueChange: (Float) -> Unit,
    rightValue: Float,
    onRightValueChange: (Float) -> Unit,
) {
    val height = 25.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .background(Color(0x99eeeeee))
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "TopBar:")
                VerticalPager(state = topPageState) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = topAppBarTypeList[it].name,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            HorizontalDivider()
            Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "BottomBar:")
                VerticalPager(state = bottomPageState) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = bottomAppBarTypeList[it].name,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            HorizontalDivider()
            Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Scaffold:")
                VerticalPager(state = scaffoldPageState) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = scaffoldTypeList[it].name,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            HorizontalDivider()
            Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Theme:")
                VerticalPager(state = themePageState) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = themeTypeList[it].name,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            HorizontalDivider()
            Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "snackbarHost:")
                Box(modifier = Modifier.fillMaxSize()) {
                    InputChip(
                        modifier = Modifier.align(Alignment.Center),
                        selected = false,
                        onClick = { onSnackBar() },
                        label = { Text(text = "show SnackBar") })
                }
            }
            HorizontalDivider()
            Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "contentWindowInsets left:")
                Slider(
                    modifier = Modifier.width(200.dp),
                    value = leftValue,
                    valueRange = 0f..100f,
                    onValueChange = onLeftValueChange
                )
                Text(text = leftValue.toString())
            }
            HorizontalDivider()
            Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "contentWindowInsets right:")
                Slider(
                    modifier = Modifier.width(200.dp),
                    value = rightValue,
                    valueRange = 0f..100f,
                    onValueChange = onRightValueChange
                )
                Text(text = rightValue.toString())
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ThemeSelector(
    themePageState: PagerState,
    contents: @Composable () -> Unit,
) {
    var selectedTheme by remember { mutableStateOf(themeTypeList[0]) }

    LaunchedEffect(key1 = themePageState) {
        snapshotFlow {
            themePageState.currentPage
        }.collect {
            selectedTheme = themeTypeList[it]
        }
    }

    selectedTheme.contents(contents)
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun selectTopBar(topPageState: PagerState): @Composable () -> Unit {
    var selectedTopAppBar by remember { mutableStateOf(topAppBarTypeList[0]) }

    LaunchedEffect(key1 = "") {
        snapshotFlow {
            topPageState.currentPage
        }.collect {
            selectedTopAppBar = topAppBarTypeList[it]
        }
    }

    return selectedTopAppBar.topBar
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun selectBottomBar(bottomPageState: PagerState): @Composable () -> Unit {
    var selectedBottomBar by remember { mutableStateOf(bottomAppBarTypeList[0]) }

    LaunchedEffect(key1 = bottomPageState) {
        snapshotFlow {
            bottomPageState.currentPage
        }.collect {
            selectedBottomBar = bottomAppBarTypeList[it]
        }
    }

    return selectedBottomBar.bottomBar
}

@Preview
@Composable
fun PreviewScaffoldSelector() {
    ScaffoldSelector()
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewSelector() {
    Selector(topPageState = rememberPagerState { 1 },
        bottomPageState = rememberPagerState { 1 },
        scaffoldPageState = rememberPagerState { 1 },
        themePageState = rememberPagerState { 1 },
        onSnackBar = {},
        onLeftValueChange = {},
        leftValue = 0.0f,
        rightValue = 0.0f,
        onRightValueChange = {}
    )
}