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
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.util.LensPowerPicker
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider

@Composable
fun LensPowerSection(
    modifier: Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    fontSize: TextUnit = 18.sp,
    leftLensPower: Double,
    setLeftLensPower: (Double) -> Unit,
    rightLensPower: Double,
    setRightLensPower: (Double) -> Unit,
) {
    var isShowLensPowerPicker by remember { mutableStateOf(false) }

    Column(modifier = modifier.background(MaterialTheme.colors.background)) {
        Row(
            modifier = modifier.padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 2.dp),
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
                    changeIsShowLensPowerPicker = { isShowLensPowerPicker = true },
                    setLensPower = { setLeftLensPower(it) }
                )
                SetOneLensPowerItem(
                    textColor = textColor,
                    fontSize = fontSize,
                    lensPower = rightLensPower,
                    eye = stringResource(id = R.string.right),
                    isShowLensPowerPicker = isShowLensPowerPicker,
                    changeIsShowLensPowerPicker = { isShowLensPowerPicker = true },
                    setLensPower = { setRightLensPower(it) }
                )
                Button(
                    onClick = { isShowLensPowerPicker = !isShowLensPowerPicker },
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.primary,
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
                            painter = painterResource(id = R.drawable.ic_edit),
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
    changeIsShowLensPowerPicker: () -> Unit,
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
                .background(MaterialTheme.colors.primary.copy(alpha = 0.2f), shape = RoundedCornerShape(20))
                .clickable { changeIsShowLensPowerPicker.invoke() }
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Text(
                text = lensPower.toString(),
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }
}