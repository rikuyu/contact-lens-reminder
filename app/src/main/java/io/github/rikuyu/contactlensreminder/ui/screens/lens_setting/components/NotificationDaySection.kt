package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider

@Composable
fun NotificationDaySection(
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    fontSize: TextUnit = 18.sp,
    lensPeriod: Int,
    showToast: () -> Unit,
    notificationType: Int,
    setNotificationType: (Int) -> Unit,
) {
    val leftButtonShape = RoundedCornerShape(
        topStart = 4.dp,
        bottomStart = 4.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp
    )

    val rightButtonShape = RoundedCornerShape(
        topStart = 0.dp,
        bottomStart = 0.dp,
        topEnd = 4.dp,
        bottomEnd = 4.dp
    )

    val border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)

    Column(modifier = modifier.background(MaterialTheme.colors.background)) {
        Row(
            modifier = modifier.padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.notification_day_title), color = textColor,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                fontSize = fontSize
            )
            if (lensPeriod == 1) {
                OutlinedButton(
                    onClick = { },
                    shape = leftButtonShape,
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    border = border
                ) {
                    Text(
                        text = stringResource(id = R.string.on_the_day),
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 2.dp),
                        fontSize = 16.sp
                    )
                }
                OutlinedButton(
                    onClick = { showToast() },
                    shape = rightButtonShape,
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Transparent
                    ),
                    border = border
                ) {
                    Text(
                        text = stringResource(id = R.string.before_day),
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(vertical = 2.dp),
                        fontSize = 16.sp
                    )
                }
            } else {
                listOf(
                    stringResource(id = R.string.on_the_day),
                    stringResource(id = R.string.before_day)
                ).forEachIndexed { index, text ->
                    val selected = notificationType == index

                    OutlinedButton(
                        onClick = { setNotificationType(index) },
                        shape = if (index == 0) {
                            leftButtonShape
                        } else {
                            rightButtonShape
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = if (selected) MaterialTheme.colors.primary else Color.Transparent
                        ),
                        border = border
                    ) {
                        Text(
                            text = text,
                            color = if (selected) Color.White else MaterialTheme.colors.primary,
                            modifier = Modifier.padding(vertical = 2.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
        SimpleDivider()
    }
}