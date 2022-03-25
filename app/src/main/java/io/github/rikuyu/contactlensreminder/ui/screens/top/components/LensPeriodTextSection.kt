package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.ui.theme.ColorPalette

@Composable
fun LensPeriodTextSection(
    modifier: Modifier,
    lensRemainingDays: Int,
    period: Int,
    color: Color = MaterialTheme.colors.primary,
    expiredColor: Color = ColorPalette.Red.secondaryRed,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                when (period) {
                    1 -> {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = if (lensRemainingDays < 0) expiredColor else color,
                                    fontSize = 28.sp
                                )
                            ) {
                                append(period.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = if (lensRemainingDays < 0) expiredColor else color,
                                    fontSize = 24.sp
                                )
                            ) {
                                append(" Day レンズ")
                            }
                        }
                    }
                    14 -> {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = if (lensRemainingDays < 0) expiredColor else color,
                                    fontSize = 26.sp
                                )
                            ) {
                                append("2Weeks レンズ")
                            }
                        }
                    }
                    30, 31 -> {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = if (lensRemainingDays < 0) expiredColor else color,
                                    fontSize = 26.sp
                                )
                            ) {
                                append("1Month レンズ")
                            }
                        }
                    }
                    else -> {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = if (lensRemainingDays < 0) expiredColor else color,
                                    fontSize = 28.sp
                                )
                            ) {
                                append(period.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = if (lensRemainingDays < 0) expiredColor else color,
                                    fontSize = 24.sp
                                )
                            ) {
                                append(" Days レンズ")
                            }
                        }
                    }
                }
            }
        )
    }
}