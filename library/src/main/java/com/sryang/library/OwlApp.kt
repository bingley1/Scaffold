package com.sryang.library

import androidx.compose.foundation.layout.padding
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
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar
    ) { innerPaddingModifier ->
        NavHost(
            modifier = Modifier.padding(innerPaddingModifier),
            navController = navController, startDestination = "courses/my"
        ) {
            composable("courses/featured") {

            }
            composable("courses/my") {

            }
            composable("courses/search") {

            }
        }
    }
}
