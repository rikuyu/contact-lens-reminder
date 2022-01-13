package com.example.contactlensreminder.presentation.screens.lens_setting.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.CleanBlue
import com.example.contactlensreminder.presentation.theme.PaleBlue
import com.example.contactlensreminder.presentation.util.SimpleDivider
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun SetNotificationSection(
    modifier: Modifier,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp,
    notificationTime: String,
    setNotificationTime: (String) -> Unit
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
                text = stringResource(id = R.string.notification_time), color = textColor,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                fontSize = fontSize
            )
            Box(
                modifier = Modifier
                    .background(PaleBlue, shape = RoundedCornerShape(20))
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = notificationTime,
                        color = textColor,
                        fontSize = 20.sp
                    )
                }
            }
            Button(
                onClick = { dialogState.show() },
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
            NotificationTimeDialog(
                dialogState = dialogState,
                setNotificationTime = setNotificationTime
            )
        }
        SimpleDivider()
    }
}