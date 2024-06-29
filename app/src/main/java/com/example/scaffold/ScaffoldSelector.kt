package com.example.scaffold

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.library.BottomAppBarTypes
import com.sryang.library.ScaffoldType
import com.sryang.library.ThemeTypes
//import com.sryang.library.scaffoldTypeList
import com.sryang.topappbar.TopAppBarTypes
//import com.sryang.library.bottomAppBarTypeList
//import com.sryang.library.themeTypeList
//import com.sryang.topappbar.topAppBarTypeList
import kotlinx.coroutines.launch


val actionBarType = listOf(
    FabPosition.End,
    FabPosition.EndOverlay,
    FabPosition.Start,
    FabPosition.Center,
)

val scaffoldTypeList = listOf(
    ScaffoldType.JetSnack,
    ScaffoldType.Owl,
    ScaffoldType.Home,
    ScaffoldType.Conversation,
    ScaffoldType.Article,
)

val themeTypeList = listOf(
    ThemeTypes.Youtube,
    ThemeTypes.RusticTheme,
    ThemeTypes.JetSurvey,
    ThemeTypes.JetCaster,
    ThemeTypes.Twitter,
    ThemeTypes.Yellow,
    ThemeTypes.Pink,
    ThemeTypes.JetSnack,
)

/** top app bar 종류별 리스트 */
val topAppBarTypeList = listOf(
    TopAppBarTypes.None,
    TopAppBarTypes.Youtube,
    TopAppBarTypes.Home,
    TopAppBarTypes.Facebook,
    TopAppBarTypes.Simple,
    TopAppBarTypes.Survey,
    TopAppBarTypes.ChannelName
)

/** bottom app bar 종류별 리스트 */
val bottomAppBarTypeList = listOf(
    BottomAppBarTypes.None,
    BottomAppBarTypes.Youtube,
    BottomAppBarTypes.Owl,
    BottomAppBarTypes.Survey,
    BottomAppBarTypes.JetSnack
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScaffoldSelector() {
    var selectedScaffold by remember { mutableStateOf(scaffoldTypeList[0]) }

    /** TopAppBar 라이브러리에서 제공하는 샘플 리스트를 pageState 형태로 선언 */
    val topPageState = rememberPagerState { topAppBarTypeList.size }

    /** BottomAppBar 라이브러리에서 제공하는 샘플 리스트를 pageState 형태로 선언 */
    val bottomPageState = rememberPagerState { bottomAppBarTypeList.size }
    val scaffoldPageState = rememberPagerState { scaffoldTypeList.size }
    val themePageState = rememberPagerState { themeTypeList.size }
    val actionButtonPageState = rememberPagerState { actionBarType.size }

    /** 1. snackBar 사용을 위한 상태 값 (샘플 코드 참조) */
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var leftValue by remember { mutableFloatStateOf(0f) }
    var rightValue by remember { mutableFloatStateOf(0f) }
    var topValue by remember { mutableFloatStateOf(0f) }
    var bottomValue by remember { mutableFloatStateOf(0f) }


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
            //Scaffold 라이브러리 제공 인터페이스 호출
            selectedScaffold.scaffold(
                selectTopBar(topPageState), // topbar pager에서 현재 position에 대한 TopAppBar compose 호출
                selectBottomBar(bottomPageState), // bottombar pager에서 현재 position에 대한 BottomAppBar compose 호출
                {
                    //2. snackBar 사용을 위한 Host compose (샘플 코드 참조)
                    SnackbarHost(hostState = snackbarHostState)
                },
                WindowInsets(
                    left = leftValue.dp,
                    right = rightValue.dp,
                    top = topValue.dp,
                    bottom = bottomValue.dp
                ),
                actionButtonPosition(actionButtonPageState),
                {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                },
                {
                    //CustomButtons()
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                onClick = { /*TODO*/ }) {
                                Text(text = "Inset Test")
                            }
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                },
            )
        }
    }

    Selector(topPageState, bottomPageState, scaffoldPageState, themePageState, onSnackBar = {
        scope.launch {
            // 3. snackBar 사용을 위한 호출 코드(이 이벤트를 통해 화면에 snackBar가 나옴.)
            snackbarHostState.showSnackbar("Snackbar")
        }
    }, onLeftValueChange = { leftValue = it },
        leftValue = leftValue,
        rightValue = rightValue,
        onRightValueChange = { rightValue = it },
        onBottomValueChange = { bottomValue = it },
        onTopValueChange = { topValue = it },
        topValue = topValue,
        bottomValue = bottomValue,
        actionButtonState = actionButtonPageState
    )
}

/**
 * Scaffold의 파라미터 별로 기능을 확인 할 수 있는 Selector
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Selector(
    topPageState: PagerState,
    bottomPageState: PagerState,
    scaffoldPageState: PagerState,
    themePageState: PagerState,
    actionButtonState: PagerState,
    onSnackBar: () -> Unit,
    leftValue: Float,
    onLeftValueChange: (Float) -> Unit,
    rightValue: Float,
    onRightValueChange: (Float) -> Unit,
    topValue: Float,
    onTopValueChange: (Float) -> Unit,
    bottomValue: Float,
    onBottomValueChange: (Float) -> Unit,
) {
    val height = 25.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var show by remember { mutableStateOf(true) }
        Column(
            Modifier
                .background(Color(0x99eeeeee))
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Column {
                Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "test panel show:")
                    Box(modifier = Modifier.fillMaxSize()) {
                        InputChip(
                            modifier = Modifier.align(Alignment.Center),
                            selected = false,
                            onClick = { show = !show },
                            label = { Text(text = "$show") })
                    }
                }
                HorizontalDivider()
            }
            if (show)
                Column {
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
                    HorizontalDivider()
                    Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "ActionBar:")
                        VerticalPager(state = actionButtonState) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = actionBarType[it].toString(),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                    HorizontalDivider()
                    Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "snackbarHost:")
                        Box(modifier = Modifier.fillMaxSize()) {
                            InputChip(
                                modifier = Modifier.align(Alignment.Center),
                                selected = false,
                                onClick = { onSnackBar() },
                                label = { Text(text = "show SnackBar") })
                        }
                    }
                    HorizontalDivider()
                    Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "contentWindowInsets left:")
                        Slider(
                            modifier = Modifier.width(200.dp),
                            value = leftValue,
                            valueRange = 0f..100f,
                            onValueChange = onLeftValueChange
                        )
                        Text(text = leftValue.toString())
                    }
                    HorizontalDivider()
                    Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "contentWindowInsets right:")
                        Slider(
                            modifier = Modifier.width(200.dp),
                            value = rightValue,
                            valueRange = 0f..100f,
                            onValueChange = onRightValueChange
                        )
                        Text(text = rightValue.toString())
                    }
                    HorizontalDivider()
                    Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "contentWindowInsets top:")
                        Slider(
                            modifier = Modifier.width(200.dp),
                            value = topValue,
                            valueRange = 0f..100f,
                            onValueChange = onTopValueChange
                        )
                        Text(text = topValue.toString())
                    }
                    HorizontalDivider()
                    Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "contentWindowInsets bottom:")
                        Slider(
                            modifier = Modifier.width(200.dp),
                            value = bottomValue,
                            valueRange = 0f..100f,
                            onValueChange = onBottomValueChange
                        )
                        Text(text = bottomValue.toString())
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
private fun selectTopBar(topPageState: PagerState): @Composable (() -> Unit)? {
    var selectedTopAppBar by remember { mutableStateOf(topAppBarTypeList[0]) }

    // Pager에서 스크롤 시 현재 position을 확인 하는 코드
    LaunchedEffect(key1 = "") {
        snapshotFlow {
            topPageState.currentPage //현재 페이지 감지
        }.collect {
            // 페이지 변경 시 position event가 발생하는 곳
            selectedTopAppBar = topAppBarTypeList[it]
        }
    }

    return selectedTopAppBar.topBar
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun actionButtonPosition(actionPositionPageState: PagerState): FabPosition {
    var fabPosition by remember { mutableStateOf(actionBarType[0]) }

    LaunchedEffect(key1 = "") {
        snapshotFlow {
            actionPositionPageState.currentPage
        }.collect {
            fabPosition = actionBarType[it]
        }
    }

    return fabPosition
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun selectBottomBar(bottomPageState: PagerState): @Composable (() -> Unit)? {
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

@Preview
@Composable
fun PreviewScaffoldSelector() {
    ScaffoldSelector()
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewSelector() {
    Selector(topPageState = rememberPagerState { 1 },
        bottomPageState = rememberPagerState { 1 },
        scaffoldPageState = rememberPagerState { 1 },
        themePageState = rememberPagerState { 1 },
        onSnackBar = {},
        onLeftValueChange = {},
        leftValue = 0.0f,
        rightValue = 0.0f,
        topValue = 0.0f,
        bottomValue = 0.0f,
        onRightValueChange = {},
        onBottomValueChange = {},
        onTopValueChange = {},
        actionButtonState = rememberPagerState { 1 }
    )
}