package com.sryang.library

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

object ScaffoldProvider

sealed class ScaffoldType(
    val name: String,
    val scaffold: @Composable (
        topBar: @Composable () -> Unit,
        bottomBar: @Composable () -> Unit,
        snackbarHost: @Composable () -> Unit,
        contentWindowInsets: WindowInsets,
        contents: @Composable () -> Unit
    ) -> Unit
) {
    // @formatter:off
    object JetSnack : ScaffoldType("JetSnack", { topBar, bottomBar, snackbarHost, contentWindowInsets, contents -> ScaffoldProvider.JetsnackScaffold(topBar = topBar, bottomBar = bottomBar, snackbarHost = snackbarHost, content = contents, contentWindowInsets = contentWindowInsets) })
    object Owl : ScaffoldType("Owl", {topBar, bottomBar, snackbarHost, contentWindowInsets, contents -> ScaffoldProvider.OwlApp(topBar = topBar, bottomBar = bottomBar, snackbarHost = snackbarHost, contents = contents, contentWindowInsets = contentWindowInsets) })
    object Home : ScaffoldType("Home", {topBar, bottomBar, snackbarHost, contentWindowInsets, contents -> ScaffoldProvider.HomeScreen (topBar = topBar, bottomBar = bottomBar, snackbarHost = snackbarHost, contents = contents, contentWindowInsets = contentWindowInsets) })
    object Conversation : ScaffoldType("Conversation", {topBar, bottomBar, snackbarHost, contentWindowInsets, contents -> ScaffoldProvider.ConversationContent (topBar = topBar, bottomBar = bottomBar, snackbarHost = snackbarHost, contents = contents, contentWindowInsets = contentWindowInsets) })
    @OptIn(ExperimentalMaterial3Api::class)
    object Article : ScaffoldType("Article", { topBar, bottomBar, snackbarHost, contentWindowInsets, contents -> ScaffoldProvider.ArticleScreenContent (topBar = topBar, bottomBar = bottomBar, contents = contents, snackbarHost = snackbarHost, contentWindowInsets = contentWindowInsets) })
    // @formatter:on
}

val scaffoldTypeList = listOf(
    ScaffoldType.JetSnack,
    ScaffoldType.Owl,
    ScaffoldType.Home,
    ScaffoldType.Conversation,
    ScaffoldType.Article,
)