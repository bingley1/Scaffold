package com.example.scaffold

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sryang.library.bottomAppBarTypeList
import com.sryang.library.scaffoldTypeList
import com.sryang.library.themeTypeList
import com.sryang.topappbar.topAppBarTypeList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScaffoldSelector() {
    var selectedScaffold by remember { mutableStateOf(scaffoldTypeList[0]) }
    val topPageState = rememberPagerState { topAppBarTypeList.size }
    val scaffoldPageState = rememberPagerState { scaffoldTypeList.size }
    val bottomPageState = rememberPagerState { bottomAppBarTypeList.size }
    val themePageState = rememberPagerState { themeTypeList.size }


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
                selectBottomBar(bottomPageState)
            )
        }
    }

    Selector(topPageState, bottomPageState, scaffoldPageState, themePageState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Selector(
    topPageState: PagerState,
    bottomPageState: PagerState,
    scaffoldPageState: PagerState,
    themePageState: PagerState
) {
    val height = 40.dp
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .background(Color(0xFFeeeeee))
                .width(300.dp)
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
