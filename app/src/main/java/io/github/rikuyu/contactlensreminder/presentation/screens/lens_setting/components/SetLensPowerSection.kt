package io.github.rikuyu.contactlensreminder.presentation.screens.lens_setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.presentation.util.LensPowerPicker
import io.github.rikuyu.contactlensreminder.presentation.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.presentation.theme.PaleBlue
import io.github.rikuyu.contactlensreminder.presentation.util.SimpleDivider

@Composable
fun SetLensPowerSection(
    modifier: Modifier,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp,
    leftLensPower: Double,
    setLeftLensPower: (Double) -> Unit,
    rightLensPower: Double,
    setRightLensPower: (Double) -> Unit,
    isShowLensPowerPicker: Boolean,
    changeIsShowPowerPicker: () -> Unit
) {
    Column(modifier = modifier.background(Color.White)) {
        Row(
            modifier = modifier
                .background(Color.White)
                .padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )
            Row(
                modifier = Modifier.weight(3f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SetOneLensPowerItem(
                    textColor = textColor,
                    fontSize = fontSize,
                    lensPower = leftLensPower,
                    eye = stringResource(id = R.string.left),
                    isShowLensPowerPicker = isShowLensPowerPicker,
                    setLensPower = { setLeftLensPower(it) }
                )
                SetOneLensPowerItem(
                    textColor = textColor,
                    fontSize = fontSize,
                    lensPower = rightLensPower,
                    eye = stringResource(id = R.string.right),
                    isShowLensPowerPicker = isShowLensPowerPicker,
                    setLensPower = { setRightLensPower(it) }
                )
                Button(
                    onClick = changeIsShowPowerPicker,
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = CleanBlue,
                        contentColor = Color.White,
                        disabledContentColor = Color.LightGray
                    ),
                    modifier = Modifier.padding(start = 4.dp),
                    shape = RoundedCornerShape(20)
                ) {
                    if (isShowLensPowerPicker) {
                        Text(
                            text = stringResource(id = R.string.ok),
                            fontSize = fontSize
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
        SimpleDivider()
    }
}

@Composable
fun SetOneLensPowerItem(
    textColor: Color,
    fontSize: TextUnit,
    eye: String,
    isShowLensPowerPicker: Boolean,
    lensPower: Double,
    setLensPower: (Double) -> Unit,
) {
    val lensPowerList = mutableListOf(-3.00)

    repeat(20) {
        lensPowerList.add(lensPowerList[0] - (0.25 * (it + 1)))
    }

    Text(text = eye, color = textColor, fontSize = fontSize)

    if (isShowLensPowerPicker) {
        LensPowerPicker(
            value = lensPower,
            range = lensPowerList,
            onValueChange = setLensPower
        )
    } else {
        Box(
            modifier = Modifier
                .background(PaleBlue, shape = RoundedCornerShape(20))
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Text(
                text = lensPower.toString(),
                color = textColor,
                fontSize = 20.sp
            )
        }
    }
}