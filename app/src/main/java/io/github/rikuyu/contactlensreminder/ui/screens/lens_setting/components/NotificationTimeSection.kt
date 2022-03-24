package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider
import java.time.LocalTime

@Composable
fun NotificationTimeSection(
    isDarkTheme: Boolean,
    themeColor: ThemeColor,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    fontSize: TextUnit = 18.sp,
    notificationTimeHour: Int,
    notificationTimeMinute: Int,
    setNotificationTimeHour: (Int) -> Unit,
    setNotificationTimeMinute: (Int) -> Unit,
) {
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(LocalContext.current.getString(R.string.btn_ok))
            negativeButton(LocalContext.current.getString(R.string.cancel))
        }
    ) {
        timepicker(
            initialTime = LocalTime.of(notificationTimeHour, notificationTimeMinute),
            colors = TimePickerDefaults.colors(
                activeBackgroundColor = themeColor.color,
                activeTextColor = Color.White,
                inactiveTextColor = MaterialTheme.colors.onBackground.copy(0.5f),
                headerTextColor = MaterialTheme.colors.primary,
                borderColor = MaterialTheme.colors.primary,
            ),
            is24HourClock = true
        ) { time ->
            val (hour, min) = time.toString().split(":").map(String::toInt)
            setNotificationTimeHour(hour)
            setNotificationTimeMinute(min)
        }
    }

    Column(modifier = modifier.background(MaterialTheme.colors.background)) {
        Row(
            modifier = modifier.padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.notification_time),
                color = textColor,
                fontSize = fontSize,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.primary.copy(alpha = if (isDarkTheme) 0.7f else 0.2f),
                        shape = RoundedCornerShape(20)
                    )
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { dialogState.show() }
                ) {
                    Text(
                        text = "$notificationTimeHour : ${
                        if (notificationTimeMinute < 10)
                            "0$notificationTimeMinute"
                        else notificationTimeMinute
                        }",
                        color = textColor,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                }
            }
            Button(
                onClick = { dialogState.show() },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray
                ),
                modifier = Modifier.padding(start = 16.dp),
                shape = RoundedCornerShape(20)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        SimpleDivider()
    }
}