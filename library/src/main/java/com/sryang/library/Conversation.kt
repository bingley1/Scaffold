package com.sryang.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@Composable
fun ScaffoldProvider.ConversationContent(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    contents: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    contentWindowInsets: WindowInsets,
) {
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        // Exclude ime and navigation bar padding so this can be added by the UserInput composable
        contentWindowInsets = contentWindowInsets,
        floatingActionButton = floatingActionButton
        //ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.navigationBars).exclude(WindowInsets.ime)
        , floatingActionButtonPosition = floatingActionButtonPosition
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            contents.invoke()
        }
    }
}