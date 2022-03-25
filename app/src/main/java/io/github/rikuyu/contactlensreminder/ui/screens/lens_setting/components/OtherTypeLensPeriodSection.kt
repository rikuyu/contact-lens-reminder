package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider

@Composable
fun OtherTypeLensPeriodSection(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    fontSize: TextUnit = 18.sp,
    period: Int,
    setLensPeriod: (Int) -> Unit,
) {
    var isShowLensPeriodPicker by remember { mutableStateOf(false) }

    Column(modifier = modifier.background(MaterialTheme.colors.background)) {
        Row(
            modifier = modifier.padding(top = 0.dp, bottom = 14.dp, end = 12.dp, start = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            if (isShowLensPeriodPicker) {
                NumberPicker(
                    value = period,
                    onValueChange = { setLensPeriod(it) },
                    range = 1..31,
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onSurface)
                )
            } else {
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
                        modifier = Modifier.clickable { isShowLensPeriodPicker = true }
                    ) {
                        Text(
                            text = period.toString(),
                            color = textColor,
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(id = R.string.lens_period),
                            color = textColor,
                            fontSize = fontSize,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
            Button(
                onClick = { isShowLensPeriodPicker = !isShowLensPeriodPicker },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = MaterialTheme.colors.primary,
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
                        painter = painterResource(id = R.drawable.ic_date),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        SimpleDivider()
    }
}