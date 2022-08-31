package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LensPowerToggleSection(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colors.onSurface,
    fontSize: TextUnit = 18.sp,
    flag: Boolean,
    checkedColor: Color = MaterialTheme.colors.primary,
    unCheckedColor: Color = MaterialTheme.colors.secondary,
    changeSwitch: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .clickable { changeSwitch() }
            .padding(top = 10.dp, bottom = 10.dp, end = 12.dp, start = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            color = textColor, fontSize = fontSize
        )
        Switch(
            checked = flag,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 12.dp),
            onCheckedChange = { changeSwitch() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = checkedColor,
                checkedTrackColor = checkedColor,
                uncheckedThumbColor = unCheckedColor,
                uncheckedTrackColor = unCheckedColor
            )
        )
    }
}