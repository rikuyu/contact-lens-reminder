package io.github.rikuyu.contactlensreminder.presentation.screens.lens_setting.components

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
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.presentation.screens.lens_setting.SettingEvent
import io.github.rikuyu.contactlensreminder.presentation.screens.lens_setting.SettingViewModel
import io.github.rikuyu.contactlensreminder.presentation.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.presentation.theme.SmoothGray
import io.github.rikuyu.contactlensreminder.presentation.util.Routes
import io.github.rikuyu.contactlensreminder.presentation.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.presentation.util.SimpleSpacer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LensSettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val settingValue = viewModel.setting.value

    var lensType by remember { mutableStateOf(settingValue.lensType) }

    var isUseNotification by remember { mutableStateOf(settingValue.isUseNotification) }

    var lensPeriod by remember { mutableStateOf(settingValue.lensPeriod) }

    var notificationType by remember { mutableStateOf(settingValue.notificationDay) }

    var notificationTimeHour by remember { mutableStateOf(settingValue.notificationTimeHour) }

    var notificationTimeMinute by remember { mutableStateOf(settingValue.notificationTimeMinute) }

    var leftLensPower by remember { mutableStateOf(settingValue.leftLensPower) }

    var isShowLensPowerSection by remember { mutableStateOf(settingValue.isShowLensPowerSection) }

    var rightLensPower by remember { mutableStateOf(settingValue.rightLensPower) }

    var isShowLensPeriodPicker by remember { mutableStateOf(settingValue.lensType == 2) }

    val setLensPeriod: (Int) -> Unit = { index ->
        when (index) {
            0 -> lensPeriod = 14
            1 -> lensPeriod = 31
            2 -> lensPeriod = 10
        }
    }

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
                    IconButton(onClick = { navController.navigate(Routes.TOP) }) {
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
                        setLensPeriod(it)
                        lensType = it
                        viewModel.onEvent(SettingEvent.LensType(it))
                        viewModel.onEvent(SettingEvent.LensPeriod(lensPeriod))
                        isShowLensPeriodPicker = lensType == 2
                    }
                    AnimatedVisibility(visible = !isShowLensPeriodPicker) { SimpleDivider() }
                    AnimatedVisibility(visible = isShowLensPeriodPicker) {
                        SetLensPeriodSection(
                            modifier = Modifier.fillMaxWidth(),
                            period = lensPeriod,
                            setLensPeriod = {
                                lensPeriod = it
                                viewModel.onEvent(SettingEvent.LensPeriod(it))
                            }
                        )
                    }
                    ToggleButtonSection(
                        context = context,
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.notification),
                        isShowIcon = true,
                        flag = isUseNotification
                    ) {
                        isUseNotification = !isUseNotification
                        viewModel.onEvent(SettingEvent.IsUseNotification(isUseNotification))
                    }
                    SimpleDivider()
                    AnimatedVisibility(visible = isUseNotification) {
                        Column {
                            SetNotificationDaySection(
                                modifier = Modifier.fillMaxWidth(),
                                notificationType = notificationType
                            ) {
                                notificationType = it
                                viewModel.onEvent(SettingEvent.NotificationDay(it))
                            }
                            SetNotificationTimeSection(
                                modifier = Modifier.fillMaxWidth(),
                                notificationTimeHour = notificationTimeHour,
                                setNotificationTimeHour = {
                                    notificationTimeHour = it
                                    viewModel.onEvent(SettingEvent.NotificationTimeHour(it))
                                },
                                notificationTimeMinute = notificationTimeMinute,
                                setNotificationTimeMinute = {
                                    notificationTimeMinute = it
                                    viewModel.onEvent(SettingEvent.NotificationTimeMinute(it))
                                }
                            )
                        }
                    }
                    ToggleButtonSection(
                        context = context,
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.lens_power),
                        isShowIcon = false,
                        flag = isShowLensPowerSection
                    ) {
                        isShowLensPowerSection = !isShowLensPowerSection
                        viewModel.onEvent(
                            SettingEvent.IsShowLensPowerSection(isShowLensPowerSection)
                        )
                    }
                    SimpleDivider()
                    AnimatedVisibility(visible = isShowLensPowerSection) {
                        SetLensPowerSection(
                            modifier = Modifier.fillMaxWidth(),
                            leftLensPower = leftLensPower.toDouble(),
                            setLeftLensPower = {
                                leftLensPower = it.toString()
                                viewModel.onEvent(SettingEvent.LeftPower(it.toString()))
                            },
                            rightLensPower = rightLensPower.toDouble(),
                            setRightLensPower = {
                                rightLensPower = it.toString()
                                viewModel.onEvent(SettingEvent.RightPower(it.toString()))
                            }
                        )
                    }
                }
                SetSettingButton(modifier = Modifier.fillMaxWidth()) {
                    isShowLensPeriodPicker = false
                    Toast.makeText(
                        context,
                        context.getString(R.string.success_save_setting),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.onEvent(SettingEvent.SaveSetting)
                    navController.navigate(Routes.TOP)
                }
            }
        }
    )
}