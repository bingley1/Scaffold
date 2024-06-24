package com.sryang.library

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable

object ScaffoldProvider

/**
 * @param scaffold Scaffold 타입별 제공. Scaffold의 파라미터는 자유롭게 변경을 위해 호출 시 파라미터를 설정 할 수 있게 구현(복잡함.) 라이브러리에서 제공하는 Scaffold들 내부 디자인이 없어 제공하는 의미가 없어짐.
 */
sealed class ScaffoldType(
    val name: String,
    val scaffold: @Composable (
        topBar: @Composable (() -> Unit)?,
        bottomBar: @Composable (() -> Unit)?,
        snackbarHost: @Composable () -> Unit,
        contentWindowInsets: WindowInsets,
        fabPosition: FabPosition,
        floatingActionButton: @Composable (() -> Unit)?,
        contents: @Composable () -> Unit
    ) -> Unit
) {
    // @formatter:off
    object JetSnack : ScaffoldType("JetSnack", { topBar, bottomBar, snackbarHost, contentWindowInsets, fabPosition , floatingActionButton, contents -> ScaffoldProvider.JetsnackScaffold(topBar = topBar?: {}, bottomBar = bottomBar ?: {}, snackbarHost = snackbarHost, content = contents, contentWindowInsets = contentWindowInsets, floatingActionButton = floatingActionButton ?: {}, floatingActionButtonPosition = fabPosition) })
    object Owl : ScaffoldType("Owl", {topBar, bottomBar, snackbarHost, contentWindowInsets, fabPosition , floatingActionButton,  contents -> ScaffoldProvider.OwlApp(topBar = topBar?: {}, bottomBar = bottomBar ?: {}, snackbarHost = snackbarHost, contents = contents, contentWindowInsets = contentWindowInsets, floatingActionButton = floatingActionButton ?: {}, floatingActionButtonPosition = fabPosition) })
    object Home : ScaffoldType("Home", {topBar, bottomBar, snackbarHost, contentWindowInsets, fabPosition , floatingActionButton,  contents -> ScaffoldProvider.HomeScreen (topBar = topBar?: {}, bottomBar = bottomBar ?: {}, snackbarHost = snackbarHost, contents = contents, contentWindowInsets = contentWindowInsets, floatingActionButton = floatingActionButton ?: {}, floatingActionButtonPosition = fabPosition) })
    object Conversation : ScaffoldType("Conversation", {topBar, bottomBar, snackbarHost, contentWindowInsets, fabPosition , floatingActionButton,  contents -> ScaffoldProvider.ConversationContent (topBar = topBar?: {}, bottomBar = bottomBar ?: {}, snackbarHost = snackbarHost, contents = contents, contentWindowInsets = contentWindowInsets, floatingActionButton = floatingActionButton ?: {}, floatingActionButtonPosition = fabPosition) })
    @OptIn(ExperimentalMaterial3Api::class)
    object Article : ScaffoldType("Article", { topBar, bottomBar, snackbarHost, contentWindowInsets, fabPosition , floatingActionButton,  contents -> ScaffoldProvider.ArticleScreenContent (topBar = topBar?: {}, bottomBar = bottomBar ?: {}, contents = contents, snackbarHost = snackbarHost, contentWindowInsets = contentWindowInsets, floatingActionButton = floatingActionButton ?: {}, floatingActionButtonPosition = fabPosition) })
    // @formatter:on
}

val scaffoldTypeList = listOf(
    ScaffoldType.JetSnack,
    ScaffoldType.Owl,
    ScaffoldType.Home,
    ScaffoldType.Conversation,
    ScaffoldType.Article,
)