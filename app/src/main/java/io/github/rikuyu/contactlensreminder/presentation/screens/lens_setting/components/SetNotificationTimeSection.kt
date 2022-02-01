package io.github.rikuyu.contactlensreminder.presentation.screens.lens_setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.presentation.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.presentation.theme.PaleBlue
import io.github.rikuyu.contactlensreminder.presentation.util.SimpleDivider

@Composable
fun SetNotificationTimeSection(
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp,
    notificationTimeHour: Int,
    notificationTimeMinute: Int,
    setNotificationTimeHour: (Int) -> Unit,
    setNotificationTimeMinute: (Int) -> Unit
) {
    val dialogState = rememberMaterialDialogState(false)

    Column(
        modifier = modifier.background(Color.White)
    ) {
        Row(
            modifier = modifier.padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.notification_time),
                color = textColor,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
                    .background(Color.White)
            )
            Box(
                modifier = Modifier
                    .background(PaleBlue, shape = RoundedCornerShape(20))
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = notificationTimeHour.toString(),
                        color = textColor,
                        fontSize = 20.sp
                    )
                    Text(
                        text = stringResource(id = R.string.time_div),
                        color = textColor,
                        fontSize = fontSize,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Text(
                        text = if (notificationTimeMinute == 0)
                            "0$notificationTimeMinute"
                        else notificationTimeMinute.toString(),
                        color = textColor,
                        fontSize = 20.sp
                    )
                }
            }
            NotificationTimeDialog(dialogState = dialogState, setNotificationTime = {
                val (hour, min) = it.split(":").map(String::toInt)
                setNotificationTimeHour(hour)
                setNotificationTimeMinute(min)
            })
            Button(
                onClick = { dialogState.showing = true },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = CleanBlue,
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