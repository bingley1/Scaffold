package com.sryang.library

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.supportWideScreen() = this
    .fillMaxWidth()
    .wrapContentWidth(align = Alignment.CenterHorizontally)
    .widthIn(max = 840.dp)

data class SurveyScreenData(
    val questionIndex: Int = 0,
    val questionCount: Int = 5,
    val shouldShowPreviousButton: Boolean = false,
    val shouldShowDoneButton: Boolean = true,
    val surveyQuestion: SurveyQuestion = SurveyQuestion.FREE_TIME,
)

enum class SurveyQuestion {
    FREE_TIME,
    SUPERHERO,
    LAST_TAKEAWAY,
    FEELING_ABOUT_SELFIES,
    TAKE_SELFIE,
}


@Composable
fun SurveyQuestionsScreen(
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable ((PaddingValues) -> Unit)? = null,
    bottomBar: @Composable () -> Unit = {},
) {

    Surface(modifier = Modifier.supportWideScreen()) {
        Scaffold(
            topBar = { topBar?.invoke() },
            content = { content?.invoke(it) },
            bottomBar = bottomBar
        )
    }
}

