package com.example.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.scaffold.jetcaster.theme.JetcasterShapes
import com.example.scaffold.jetcaster.theme.JetcasterTheme
import com.example.scaffold.jetcaster.theme.JetcasterTypography
import com.example.scaffold.jetsurvey.theme.JetsurveyTheme
import com.example.scaffold.owl.theme.PinkTheme
import com.example.scaffold.owl.theme.TwitterTheme
import com.example.scaffold.owl.theme.YellowTheme
import com.example.scaffold.owl.theme.black200
import com.example.scaffold.owl.theme.black400
import com.example.scaffold.owl.theme.black600
import com.sryang.library.ArticleScreenContent
import com.sryang.library.BottomBarsProvider
import com.sryang.library.ConversationContent
import com.sryang.library.CourseTabs
import com.sryang.library.HomeScreen
import com.sryang.library.HomeSections
import com.sryang.library.JetsnackBottomBar
import com.sryang.library.JetsnackScaffold
import com.sryang.library.OwlApp
import com.sryang.library.OwlBottomBar
import com.sryang.library.SurveyBottomBar
import com.sryang.library.SurveyQuestionsScreen
import com.sryang.library.SurveyScreenData
import com.sryang.library.ThemeProvider
import com.sryang.library.Twitter
import com.sryang.topappbar.ChannelNameBar
import com.sryang.topappbar.HomeAppBar
import com.sryang.topappbar.ScaffoldTopAppBar
import com.sryang.topappbar.SurveyTopAppBar
import com.sryang.topappbar.TopAppBar
import com.sryang.topappbar.TopAppBarProvider

class MainActivity : ComponentActivity() {

    val topBars = listOf(
        "None", "TopAppBar", "ScaffoldTopAppBar", "HomeAppBar", "SurveyTopAppBar", "ChannelNameBar"
    )

    val scaffolds = listOf(
        "ScaffoldExample", "OwlApp", "HomeScreen", "ConversationContent", "SurveyQuestionsScreen"
    )

    val bottomBars = listOf(
        "None",
        "SurveyBottomBar",
        "JetsnackBottomBar",
        "OwlBottomBar",
    )

    val themes = listOf(
        "TwitterTheme", "PinkTheme", "YellowTheme", "JetsurveyTheme", "JetcasterTheme"
    )

    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var theme by remember { mutableStateOf(themes[0]) }
            var selectedTopAppBar by remember { mutableStateOf(topBars[0]) }
            var selectedScaffold by remember { mutableStateOf(scaffolds[0]) }
            var selectedBottomBar by remember { mutableStateOf(bottomBars[0]) }

            val topPageState = rememberPagerState { topBars.size }
            val scaffoldPageState = rememberPagerState { scaffolds.size }
            val bottomPageState = rememberPagerState { bottomBars.size }
            val themePageState = rememberPagerState { themes.size }

            LaunchedEffect(key1 = topBars) {
                snapshotFlow {
                    topPageState.currentPage
                }.collect {
                    selectedTopAppBar = topBars[it]
                }
            }

            LaunchedEffect(key1 = scaffolds) {
                snapshotFlow {
                    scaffoldPageState.currentPage
                }.collect {
                    selectedScaffold = scaffolds[it]
                }
            }

            LaunchedEffect(key1 = bottomPageState) {
                snapshotFlow {
                    bottomPageState.currentPage
                }.collect {
                    selectedBottomBar = bottomBars[it]
                }
            }

            LaunchedEffect(key1 = themePageState) {
                snapshotFlow {
                    themePageState.currentPage
                }.collect {
                    theme = themes[it]
                }
            }


            ThemeChange(theme = theme) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                ) {
                    val topBar: @Composable () -> Unit = {
                        when (selectedTopAppBar) {
                            "TopAppBar" -> TopAppBarProvider.TopAppBar()
                            "ScaffoldTopAppBar" -> TopAppBarProvider.ScaffoldTopAppBar()
                            "HomeAppBar" -> TopAppBarProvider.HomeAppBar()
                            "SurveyTopAppBar" -> TopAppBarProvider.SurveyTopAppBar()
                            "ChannelNameBar" -> TopAppBarProvider.ChannelNameBar()
                        }
                    }

                    val bottomBar: @Composable () -> Unit = {
                        when (selectedBottomBar) {
                            "SurveyBottomBar" -> {
                                BottomBarsProvider.SurveyBottomBar(true,
                                    shouldShowDoneButton = true,
                                    isNextButtonEnabled = true,
                                    onPreviousPressed = {},
                                    onNextPressed = {},
                                    onDonePressed = {})
                            }

                            "JetsnackBottomBar" -> BottomBarsProvider.JetsnackBottomBar(tabs = arrayOf(
                                HomeSections.FEED,
                                HomeSections.CART,
                                HomeSections.SEARCH,
                                HomeSections.PROFILE
                            ), currentRoute = HomeSections.FEED.route, navigateToRoute = { })

                            "OwlBottomBar" -> BottomBarsProvider.OwlBottomBar(navController = rememberNavController(),
                                remember { CourseTabs.values() })
                        }
                    }

                    when (selectedScaffold) {
                        "ScaffoldExample" -> ScaffoldExample(topBar = topBar, bottomBar = bottomBar)
                        "OwlApp" -> OwlApp(topBar = topBar, bottomBar = bottomBar)
                        "HomeScreen" -> HomeScreen(topBar = topBar, bottomBar = bottomBar)
                        "ConversationContent" -> ConversationContent(
                            topBar = topBar, bottomBar = bottomBar
                        )

                        "SurveyQuestionsScreen" -> SurveyQuestionsScreen(
                            topBar = topBar, bottomBar = bottomBar
                        )
                    }
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    Modifier
                        .background(Color(0xFFeeeeee))
                        .width(300.dp)
                        .align(Alignment.Center)
                ) {
                    Row(Modifier.height(80.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "TopBar:")
                        VerticalPager(state = topPageState) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = topBars[it], modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                    HorizontalDivider()
                    Row(Modifier.height(80.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "BottomBar:")
                        VerticalPager(state = bottomPageState) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = bottomBars[it],
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                    HorizontalDivider()
                    Row(Modifier.height(80.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Scaffold:")
                        VerticalPager(state = scaffoldPageState) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = scaffolds[it],
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                    HorizontalDivider()
                    Row(Modifier.height(80.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Theme:")
                        VerticalPager(state = themePageState) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = themes[it], modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeChange(
    theme: String,
    contents: @Composable () -> Unit,
) {
    when (theme) {
        "TwitterTheme" -> TwitterTheme(content = contents)
        "PinkTheme" -> PinkTheme(content = contents)
        "YellowTheme" -> YellowTheme(content = contents)
        "JetsurveyTheme" -> JetsurveyTheme(content = contents)
        "JetcasterTheme" -> JetcasterTheme(content = contents)
    }
}


@Preview
@Composable
fun TwitterScaffoldExample() {
    ThemeProvider.Twitter { ScaffoldExample() }
}

////////////////

@Preview
@Composable
private fun HomeAppBarPreview3() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = black600,
            surface = black400,
            background = black200,
//            onPrimary = black200,
//            primaryContainer = black600,
//            secondary = black600,
//            secondaryContainer = black600,
//            onSurface = black600,
//            onBackground = black600,
//            error = black600,
//            onError = black600,
//            surfaceTint = black600,
//            inverseSurface = black600,
//            inverseOnSurface = black600,
//            outline = black600,
//            scrim = black600,
//            errorContainer = black600,
//            onErrorContainer = black600,
//            outlineVariant = black600,
        ),
        shapes = JetcasterShapes,
        typography = JetcasterTypography,
    ) {
        HomeScreen(topBar = { TopAppBarProvider.HomeAppBar() })
    }
}