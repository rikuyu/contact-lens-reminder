package io.github.rikuyu.contactlensreminder.ui.screens.app_setting.color_theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor

@Composable
fun ColorPickerDialog(
    color: Color = MaterialTheme.colors.primary,
    stateColor: ThemeColor,
    dialogState: Boolean,
    changeDialogState: (Boolean) -> Unit,
    changeThemeColor: (ThemeColor) -> Unit,
) {
    if (dialogState) {
        Dialog(onDismissRequest = { }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.background)
                    .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(id = R.string.color_theme_dialog_title),
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeColorItem(stateColor, ThemeColor.Blue, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.Navy, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.Cyan, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.PeacockBlue, changeThemeColor)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeColorItem(stateColor, ThemeColor.Green, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.YellowGreen, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.Yellow, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.Orange, changeThemeColor)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeColorItem(stateColor, ThemeColor.Brown, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.Purple, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.Pink, changeThemeColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(stateColor, ThemeColor.Red, changeThemeColor)
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    TextButton(
                        onClick = { changeDialogState(false) }
                    ) {
                        Text(text = stringResource(id = R.string.btn_ok), color = color)
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeColorItem(
    color: ThemeColor,
    backgroundColor: ThemeColor,
    changeThemeColor: (ThemeColor) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(6.dp)
            .size(54.dp)
            .clip(CircleShape)
            .background(backgroundColor.color)
            .clickable { changeThemeColor(backgroundColor) }
    ) {
        if (backgroundColor == color) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}