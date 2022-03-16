package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.ui.theme.PaleBlue
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.ui.util.StringNumberPicker

@Composable
fun NotificationTimeSection(
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    fontSize: TextUnit = 18.sp,
    notificationTimeHour: Int,
    notificationTimeMinute: Int,
    setNotificationTimeHour: (Int) -> Unit,
    setNotificationTimeMinute: (Int) -> Unit
) {
    var isShowLensPeriodPicker by remember { mutableStateOf(false) }

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
            if (isShowLensPeriodPicker) {
                NumberPicker(
                    value = notificationTimeHour,
                    onValueChange = { setNotificationTimeHour(it) },
                    range = 1..24,
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onSurface)
                )
                Text(text = " : ")
                StringNumberPicker(
                    value = notificationTimeMinute.toString(),
                    onValueChange = { setNotificationTimeMinute(it.toInt()) },
                    range = listOf("00", "15", "30", "45"),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onSurface)
                )
            } else {
                Box(
                    modifier = Modifier
                        .background(PaleBlue, shape = RoundedCornerShape(20))
                        .padding(vertical = 8.dp, horizontal = 10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { isShowLensPeriodPicker = true }
                    ) {
                        Text(
                            text = notificationTimeHour.toString(),
                            color = MaterialTheme.colors.onSecondary,
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(id = R.string.time_div),
                            color = MaterialTheme.colors.onSecondary,
                            fontSize = fontSize,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        Text(
                            text = if (notificationTimeMinute == 0)
                                "0$notificationTimeMinute"
                            else notificationTimeMinute.toString(),
                            color = MaterialTheme.colors.onSecondary,
                            fontSize = 20.sp
                        )
                    }
                }
            }
            Button(
                onClick = { isShowLensPeriodPicker = !isShowLensPeriodPicker },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = CleanBlue,
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray
                ),
                modifier = Modifier.padding(start = 16.dp),
                shape = RoundedCornerShape(20)
            ) {
                if (isShowLensPeriodPicker) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        fontSize = fontSize
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        SimpleDivider()
    }
}