package com.example.contactlensreminder.presentation.ui.screens.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue

@Composable
fun LensPeriodTextSection(
    modifier: Modifier,
    period: Int
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
                                    color = CleanBlue,
                                    fontSize = 28.sp
                                )
                            ) {
                                append(period.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = CleanBlue,
                                    fontSize = 24.sp
                                )
                            ) {
                                append(" Day")
                            }
                        }
                    }
                    14 -> {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = CleanBlue,
                                    fontSize = 26.sp
                                )
                            ) {
                                append("2Weeks")
                            }
                        }
                    }
                    30, 31 -> {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = CleanBlue,
                                    fontSize = 26.sp
                                )
                            ) {
                                append("1Month")
                            }
                        }
                    }
                    else -> {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = CleanBlue,
                                    fontSize = 28.sp
                                )
                            ) {
                                append(period.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = CleanBlue,
                                    fontSize = 24.sp
                                )
                            ) {
                                append(" Days")
                            }
                        }
                    }
                }
            }
        )
    }
}