package com.example.contactlensreminder.presentation.ui.screens.lenssetting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue
import com.example.contactlensreminder.presentation.ui.theme.LightBlue
import com.example.contactlensreminder.presentation.ui.theme.SmoothGray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleDivider

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LensSettingScreen(
    navController: NavController,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp
) {

    var pickerValue by remember { mutableStateOf(0) }
    var leftEyePower by remember { mutableStateOf(-4.00) }
    var rightEyePower by remember { mutableStateOf(-4.00) }

    val decideNotifyDaysState = remember {
        mutableStateOf(true)
    }

    val decideLensPeriodState = remember {
        mutableStateOf(false)
    }

    val lensPowerList = mutableListOf(-3.00)
    repeat(20) {
        lensPowerList.add(lensPowerList[0] - (0.25 * (it + 1)))
    }

    var notifyType by remember {
        mutableStateOf(0)
    }

    var periodType by remember {
        mutableStateOf(0)
    }

    val showDecideLensPeriodForm: (index: Int) -> Unit = {
        periodType = it
        decideLensPeriodState.value = periodType == 2
    }

    var isChangeLensPower by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "コンタクトレンズ設定")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.TOP) }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = CleanBlue
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SmoothGray)
            ) {
                SimpleDivider()
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(SmoothGray)
                )
                SimpleDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(all = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "期間", color = textColor,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp),
                        fontSize = fontSize
                    )
                    listOf("2Weeks", "1Month", "Other").forEachIndexed { index, item ->
                        val selected = periodType == index

                        val shape = when (index) {
                            0 -> RoundedCornerShape(
                                topStart = 4.dp,
                                bottomStart = 4.dp,
                                topEnd = 0.dp,
                                bottomEnd = 0.dp
                            )
                            2 -> RoundedCornerShape(
                                topStart = 0.dp,
                                bottomStart = 0.dp,
                                topEnd = 4.dp,
                                bottomEnd = 4.dp
                            )
                            else -> CutCornerShape(0.dp)
                        }

                        OutlinedButton(
                            onClick = { showDecideLensPeriodForm(index) },
                            shape = shape,
                            colors = ButtonDefaults.textButtonColors(backgroundColor = if (selected) CleanBlue else Color.Transparent),
                            border = BorderStroke(
                                width = 1.dp,
                                color = CleanBlue
                            )
                        ) {
                            Text(text = item, color = if (selected) Color.White else CleanBlue)
                        }
                    }
                }
                SimpleDivider()
                AnimatedVisibility(visible = decideLensPeriodState.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "レンズの交換日数", color = textColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(12.dp),
                                fontSize = fontSize
                            )
                            DaysPicker(
                                value = pickerValue,
                                onValueChange = { pickerValue = it },
                                range = 1..31
                            )
                        }
                        SimpleDivider()
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(all = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "通知",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp),
                        color = textColor, fontSize = fontSize
                    )
                    Switch(
                        checked = decideNotifyDaysState.value,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 12.dp),
                        onCheckedChange = {
                            decideNotifyDaysState.value = !decideNotifyDaysState.value
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = CleanBlue,
                            checkedTrackColor = CleanBlue,
                            uncheckedThumbColor = LightBlue,
                            uncheckedTrackColor = LightBlue
                        )
                    )
                }
                SimpleDivider()
                AnimatedVisibility(visible = decideNotifyDaysState.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "通知日", color = textColor,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 12.dp),
                                fontSize = fontSize
                            )
                            listOf("前日", "当日").forEachIndexed { index, item ->
                                val selected = notifyType == index

                                val shape = when (index) {
                                    0 -> RoundedCornerShape(
                                        topStart = 4.dp,
                                        bottomStart = 4.dp,
                                        topEnd = 0.dp,
                                        bottomEnd = 0.dp
                                    )
                                    1 -> RoundedCornerShape(
                                        topStart = 0.dp,
                                        bottomStart = 0.dp,
                                        topEnd = 4.dp,
                                        bottomEnd = 4.dp
                                    )
                                    else -> CutCornerShape(0.dp)
                                }

                                OutlinedButton(
                                    onClick = { notifyType = index },
                                    shape = shape,
                                    colors = ButtonDefaults.textButtonColors(backgroundColor = if (selected) CleanBlue else Color.Transparent),
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = CleanBlue
                                    )
                                ) {
                                    Text(
                                        text = item,
                                        color = if (selected) Color.White else CleanBlue
                                    )
                                }
                            }
                        }
                        SimpleDivider()
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(all = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "度数",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp),
                        color = textColor, fontSize = fontSize
                    )
                    Row(
                        modifier = Modifier
                            .weight(3f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "左", color = textColor, fontSize = fontSize)

                        if (isChangeLensPower) {
                            LensPowerPicker(
                                value = leftEyePower,
                                range = lensPowerList,
                                onValueChange = {
                                    leftEyePower = it
                                }
                            )
                        } else {
                            Text(
                                text = leftEyePower.toString(),
                                color = textColor,
                                fontSize = fontSize
                            )
                        }
                        Text(text = "右", color = textColor, fontSize = fontSize)
                        if (isChangeLensPower) {
                            LensPowerPicker(
                                value = rightEyePower,
                                range = lensPowerList,
                                onValueChange = {
                                    rightEyePower = it
                                }
                            )
                        } else {
                            Text(
                                text = rightEyePower.toString(),
                                color = textColor,
                                fontSize = fontSize
                            )
                        }
                        Button(
                            onClick = { isChangeLensPower = !isChangeLensPower },
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = CleanBlue,
                                contentColor = Color.White,
                                disabledContentColor = Color.LightGray
                            ),
                            modifier = Modifier.padding(start = 4.dp),
                            shape = RoundedCornerShape(20)
                        ) {
                            if (isChangeLensPower) {
                                Text(text = "OK", fontSize = fontSize)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(all = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = CleanBlue,
                            contentColor = Color.White,
                            disabledContentColor = Color.LightGray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        shape = RoundedCornerShape(20)
                    ) {
                        Text(text = "OK", fontSize = fontSize)
                    }
                }
            }
        }
    )
}