package com.sryang.library

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

object ScaffoldProvider

sealed class ScaffoldType(
    val name: String,
    val scaffold: @Composable (
        topBar: @Composable () -> Unit,
        bottomBar: @Composable () -> Unit,
        contents: @Composable () -> Unit
    ) -> Unit
) {
    // @formatter:off
    object JetSnack : ScaffoldType("JetSnack", { topBar, bottomBar, contents -> ScaffoldProvider.JetsnackScaffold(topBar = topBar, bottomBar = bottomBar, content = contents) })
    object Owl : ScaffoldType("Owl", {topBar, bottomBar, contents -> ScaffoldProvider.OwlApp(topBar = topBar, bottomBar = bottomBar, contents = contents) })
    object Home : ScaffoldType("Home", {topBar, bottomBar, contents -> ScaffoldProvider.HomeScreen (topBar = topBar, bottomBar = bottomBar, contents = contents) })
    object Conversation : ScaffoldType("Conversation", {topBar, bottomBar, contents -> ScaffoldProvider.ConversationContent (topBar = topBar, bottomBar = bottomBar, contents = contents) })
    @OptIn(ExperimentalMaterial3Api::class)
    object Article : ScaffoldType("Article", { topBar, bottomBar, contents -> ScaffoldProvider.ArticleScreenContent (topBar = topBar, bottomBar = bottomBar, contents = contents) })
    // @formatter:on
}

val scaffoldTypeList = listOf(
    ScaffoldType.JetSnack,
    ScaffoldType.Owl,
    ScaffoldType.Home,
    ScaffoldType.Conversation,
    ScaffoldType.Article,
)