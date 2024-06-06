package com.sryang.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@ExperimentalMaterial3Api
@Composable
fun ArticleScreenContent(
    bottomBarContent: @Composable () -> Unit = { },
    topAppBar: @Composable () -> Unit = { },
) {
    Scaffold(
        topBar = topAppBar,
        bottomBar = bottomBarContent
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
    }
}