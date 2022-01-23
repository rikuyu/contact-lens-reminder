package com.example.contactlensreminder.presentation.screens.lens_setting.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.CleanBlue
import com.example.contactlensreminder.presentation.theme.LightBlue
import com.example.contactlensreminder.presentation.util.makeNotificationSettingIntent

@Composable
fun ToggleButtonSection(
    context: Context,
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp,
    flag: Boolean,
    isShowIcon: Boolean,
    changeSwitch: () -> Unit
) {
    Row(
        modifier = modifier
            .background(Color.White)
            .clickable { changeSwitch() }
            .padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            color = textColor, fontSize = fontSize
        )
        if (isShowIcon) {
            IconButton(
                onClick = { context.startActivity(makeNotificationSettingIntent(context)) },
                modifier = Modifier.padding(end = 14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier.size(33.dp, 33.dp)
                )
            }
        }
        Switch(
            checked = flag,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 12.dp),
            onCheckedChange = { changeSwitch() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = CleanBlue,
                checkedTrackColor = CleanBlue,
                uncheckedThumbColor = LightBlue,
                uncheckedTrackColor = LightBlue
            )
        )
    }
}