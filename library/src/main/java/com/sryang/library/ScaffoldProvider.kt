package com.sryang.library

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

object ScaffoldProvider

sealed class ScaffoldType(
    val name: String,
    val scaffold: @Composable (@Composable () -> Unit, @Composable () -> Unit) -> Unit
) {
    object JetSnack :
        ScaffoldType("JetSnack", { topBar, bottomBar -> ScaffoldProvider.JetsnackScaffold(topBar = topBar, bottomBar = bottomBar) { } })

    object Owl : ScaffoldType("Owl", {topBar, bottomBar -> ScaffoldProvider.OwlApp(topBar = topBar, bottomBar = bottomBar) })
    object Home : ScaffoldType("Home", {topBar, bottomBar -> ScaffoldProvider.HomeScreen (topBar = topBar, bottomBar = bottomBar) })
    object Conversation : ScaffoldType("Conversation", {topBar, bottomBar -> ScaffoldProvider.ConversationContent (topBar = topBar, bottomBar = bottomBar) })
    @OptIn(ExperimentalMaterial3Api::class)
    object Article : ScaffoldType("Article", { topBar, bottomBar -> ScaffoldProvider.ArticleScreenContent (topBar = topBar, bottomBar = bottomBar) })
}

val scaffoldTypeList = listOf(
    ScaffoldType.JetSnack,
    ScaffoldType.Owl,
    ScaffoldType.Home,
    ScaffoldType.Conversation,
    ScaffoldType.Article,
)