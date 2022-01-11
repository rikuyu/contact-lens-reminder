package com.example.contactlensreminder.presentation.ui.screens.lens_setting

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.LensEvent
import com.example.contactlensreminder.presentation.MainViewModel
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue
import com.example.contactlensreminder.presentation.ui.theme.SmoothGray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleDivider
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LensSettingScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val settingValue = viewModel.setting.value

    var isUseNotification by remember { mutableStateOf(settingValue.isUseNotification) }

    var lensType by remember { mutableStateOf(settingValue.lensType) }

    var lensPeriod by remember { mutableStateOf(settingValue.lensPeriod) }

    var notificationType by remember { mutableStateOf(settingValue.notificationDay) }

    var leftLensPower by remember { mutableStateOf(settingValue.leftLensPower) }

    var rightLensPower by remember { mutableStateOf(settingValue.rightLensPower) }

    var isShowLensPeriodPicker by remember { mutableStateOf(settingValue.lensType == 2) }

    var isShowLensPowerPicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.lens_setting_screen_title),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Routes.TOP)
                        viewModel.onEvent(LensEvent.SaveSetting)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = CleanBlue
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    SimpleDivider()
                    SimpleSpacer(height = 20.dp, color = SmoothGray)
                    SimpleDivider()
                    SetLensTypeSection(
                        modifier = Modifier.fillMaxWidth(),
                        lensType = lensType
                    ) {
                        lensType = it
                        viewModel.onEvent(LensEvent.LensType(it))
                        isShowLensPeriodPicker = lensType == 2
                    }
                    AnimatedVisibility(visible = !isShowLensPeriodPicker) { SimpleDivider() }
                    AnimatedVisibility(visible = isShowLensPeriodPicker) {
                        SetChangeLensPeriodSection(
                            modifier = Modifier.fillMaxWidth(),
                            period = lensPeriod,
                            setPeriod = {
                                lensPeriod = it
                                viewModel.onEvent(LensEvent.LensPeriod(it))
                            }
                        )
                    }
                    SetUseNotification(
                        modifier = Modifier.fillMaxWidth(),
                        isUseNotification = isUseNotification
                    ) {
                        isUseNotification = !isUseNotification
                        viewModel.onEvent(LensEvent.IsUseNotification(isUseNotification))
                    }
                    SimpleDivider()
                    AnimatedVisibility(visible = isUseNotification) {
                        SetNotificationDaySection(
                            modifier = Modifier.fillMaxWidth(),
                            notificationType = notificationType
                        ) {
                            notificationType = it
                            viewModel.onEvent(LensEvent.NotificationDay(it))
                        }
                    }
                    SetLensPowerSection(
                        modifier = Modifier.fillMaxWidth(),
                        leftLensPower = leftLensPower,
                        setLeftLensPower = {
                            leftLensPower = it
                            viewModel.onEvent(LensEvent.LeftLensPower(it))
                        },
                        rightLensPower = rightLensPower,
                        setRightLensPower = {
                            rightLensPower = it
                            viewModel.onEvent(LensEvent.RightLensPower(it))
                        },
                        saveLensPower = { viewModel.onEvent(LensEvent.SaveLensPower) },
                        isShowLensPowerPicker = isShowLensPowerPicker,
                        changeIsShowPowerPicker = {
                            isShowLensPowerPicker = !isShowLensPowerPicker
                        }
                    )
                    SimpleDivider()
                }
                SetSettingButton(modifier = Modifier.fillMaxWidth()) {
                    isShowLensPowerPicker = false
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_message),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.onEvent(LensEvent.SaveSetting)
                    navController.navigate(Routes.TOP)
                }
            }
        }
    )
}