package io.github.rikuyu.contactlensreminder.ui.screens.app_setting.color_theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColorPalette

@Composable
fun ColorPickerDialog(
    color: Color = MaterialTheme.colors.primary,
    dialogState: Boolean,
    changeDialogState: (Boolean) -> Unit,
    changeThemeColor: (Boolean) -> Unit,
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
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeColorItem(ThemeColorPalette.Blue.primaryBlue)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.Navy.primaryNavy)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.Cyan.primaryCyan)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.PeacockBlue.primaryPeacockBlue)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeColorItem(ThemeColorPalette.Green.primaryGreen)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.YellowGreen.primaryYellowGreen)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.Yellow.primaryYellow)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.Orange.primaryOrange)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeColorItem(ThemeColorPalette.Brown.primaryBrown)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.Purple.primaryPurple)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.Pink.primaryPink)
                    Spacer(modifier = Modifier.width(4.dp))
                    ThemeColorItem(ThemeColorPalette.Red.primaryRed)
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
                        Text(text = stringResource(id = R.string.btn_cancel), color = color)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
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
fun ThemeColorItem(color: Color) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(6.dp)
            .size(54.dp)
            .clip(CircleShape)
            .background(color)
            .border(BorderStroke(0.6.dp, MaterialTheme.colors.onSurface), CircleShape)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}