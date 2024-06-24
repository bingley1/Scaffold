package com.sryang.library

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ScaffoldProvider.OwlApp(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    contents: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    contentWindowInsets: WindowInsets,
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        contentWindowInsets = contentWindowInsets,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition
    ) { innerPaddingModifier ->
        NavHost(
            modifier = Modifier.padding(innerPaddingModifier),
            navController = navController, startDestination = "courses/my"
        ) {
            composable("courses/featured") {
                contents.invoke()
            }
            composable("courses/my") {
                contents.invoke()
            }
            composable("courses/search") {
                contents.invoke()
            }
        }
    }
}
