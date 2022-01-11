package com.example.contactlensreminder.presentation.ui.screens.lens_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue

@Composable
fun SetLensPowerSection(
    modifier: Modifier,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp
) {
    var leftEyePower by remember { mutableStateOf(-4.00) }
    var rightEyePower by remember { mutableStateOf(-4.00) }

    var isShowLensPowerPicker by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .background(Color.White)
            .padding(all = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.lens_power),
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            color = textColor, fontSize = fontSize
        )
        Row(
            modifier = Modifier.weight(3f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SetOneLensPowerItem(
                textColor = textColor,
                fontSize = fontSize,
                lensPower = leftEyePower,
                eye = stringResource(id = R.string.left),
                isShowLensPowerPicker = isShowLensPowerPicker
            ) {
                leftEyePower = it
            }
            SetOneLensPowerItem(
                textColor = textColor,
                fontSize = fontSize,
                lensPower = rightEyePower,
                eye = stringResource(id = R.string.right),
                isShowLensPowerPicker = isShowLensPowerPicker
            ) {
                rightEyePower = it
            }
            Button(
                onClick = { isShowLensPowerPicker = !isShowLensPowerPicker },
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
}

@Composable
fun SetOneLensPowerItem(
    textColor: Color,
    fontSize: TextUnit,
    lensPower: Double,
    eye: String,
    isShowLensPowerPicker: Boolean,
    onValueChange: (power: Double) -> Unit,
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
            onValueChange = onValueChange
        )
    } else {
        Text(
            text = lensPower.toString(),
            color = textColor,
            fontSize = fontSize
        )
    }
}