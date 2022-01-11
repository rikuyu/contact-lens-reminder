package com.example.contactlensreminder.presentation.screens.lens_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.CleanBlue
import com.example.contactlensreminder.presentation.theme.LightBlue

@Composable
fun SetIsUseNotification(
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp,
    isUseNotification: Boolean,
    changeSwitch: () -> Unit
) {
    Row(
        modifier = modifier
            .background(Color.White)
            .padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.notification_title),
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            color = textColor, fontSize = fontSize
        )
        Switch(
            checked = isUseNotification,
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