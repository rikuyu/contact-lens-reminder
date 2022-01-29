package io.github.rikuyu.contactlensreminder.presentation.screens.top.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.presentation.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.presentation.theme.LightBlue
import io.github.rikuyu.contactlensreminder.presentation.theme.LightRed

@Composable
fun RemainingDaysBar(
    lensPeriod: Int,
    notificationTimeHour: Int,
    notificationTimeMinute: Int,
    isUsingContactLens: Boolean,
    lensRemainingDays: Int,
    exchangeDay: String,
    isUseNotification: Boolean,
    remainingDaysTextFontSize: TextUnit = 36.sp,
    supportTextFontSize: TextUnit = 24.sp,
    periodTextFontSize: TextUnit = 16.sp,
    supportTextColor: Color = Color.Black,
    radius: Dp = 150.dp,
    color: Color = CleanBlue,
    strokeWidth: Dp = 30.dp,
    animDuration: Int = 1500,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val remainingDays =
        animateFloatAsState(
            targetValue = if (animationPlayed) lensRemainingDays.toFloat() else 0f,
            animationSpec = tween(
                durationMillis = animDuration,
                delayMillis = animDelay
            )
        )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(radius * 2f)) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = if (lensRemainingDays > 0 || !isUsingContactLens) LightBlue else LightRed,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = if (lensRemainingDays > 0 || !isUsingContactLens) color else Color.Red,
                startAngle = -90f,
                sweepAngle = if (lensRemainingDays > 0) (remainingDays.value * (360.0 / lensPeriod)).toFloat() else 360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                    withStyle(
                        style = SpanStyle(
                            color = supportTextColor,
                            fontSize = supportTextFontSize
                        )
                    ) {
                        append(stringResource(R.string.remain))
                    }
                    withStyle(
                        style = SpanStyle(
                            color = if (lensRemainingDays > 0 || !isUsingContactLens) color else Color.Red,
                            fontSize = remainingDaysTextFontSize
                        )
                    ) {
                        append(
                            if (lensRemainingDays > 0 || !isUsingContactLens)
                                lensRemainingDays.toString()
                            else
                                lensPeriod.toString()
                        )
                    }
                    withStyle(
                        style = SpanStyle(
                            color = supportTextColor,
                            fontSize = supportTextFontSize
                        )
                    ) {
                        append(stringResource(id = R.string.day))
                    }
                    append(stringResource(id = R.string.new_line))
                    withStyle(
                        style = SpanStyle(
                            color = supportTextColor,
                            fontSize = periodTextFontSize
                        )
                    ) {
                        append(stringResource(id = R.string.change_message))
                        append(" ")
                        append(exchangeDay)
                    }
                    if (isUseNotification) {
                        append(stringResource(id = R.string.new_line))
                        withStyle(
                            style = SpanStyle(
                                color = supportTextColor,
                                fontSize = periodTextFontSize
                            )
                        ) {
                            append(stringResource(id = R.string.time_message))
                            append(" ")
                            append(notificationTimeHour.toString())
                            append(stringResource(id = R.string.time_div))
                            append(
                                if (notificationTimeMinute == 0)
                                    "0$notificationTimeMinute"
                                else notificationTimeMinute.toString()
                            )
                        }
                    }
                }
            },
            textAlign = TextAlign.Center
        )
    }
}