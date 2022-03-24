package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

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
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.LensSettingEvent
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.LensSettingViewModel
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LensSettingScreen(
    isDarkTheme: Boolean,
    navController: NavController,
    viewModelLens: LensSettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val settingValue = viewModelLens.lensSetting.value

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
            2 -> {
            }
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
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                        .weight(1f)
                ) {
                    SimpleDivider()
                    SimpleSpacer(height = 20.dp, color = MaterialTheme.colors.surface)
                    SimpleDivider()
                    LensTypeSection(
                        modifier = Modifier.fillMaxWidth(),
                        lensType = lensType
                    ) {
                        setLensPeriod(it)
                        lensType = it
                        viewModelLens.onEvent(LensSettingEvent.LensType(it))
                        viewModelLens.onEvent(LensSettingEvent.LensPeriod(lensPeriod))
                        isShowLensPeriodPicker = lensType == 2
                    }
                    AnimatedVisibility(visible = !isShowLensPeriodPicker) { SimpleDivider() }
                    AnimatedVisibility(visible = isShowLensPeriodPicker) {
                        LensPeriodOtherTypeSection(
                            isDarkTheme = isDarkTheme,
                            modifier = Modifier.fillMaxWidth(),
                            period = lensPeriod,
                            setLensPeriod = {
                                lensPeriod = it
                                viewModelLens.onEvent(LensSettingEvent.LensPeriod(it))
                            }
                        )
                    }
                    ToggleButtonSection(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.notification),
                        isUseNotification = isUseNotification,
                        flag = isUseNotification
                    ) {
                        isUseNotification = !isUseNotification
                        viewModelLens.onEvent(LensSettingEvent.IsUseNotification(isUseNotification))
                    }
                    SimpleDivider()
                    AnimatedVisibility(visible = isUseNotification) {
                        Column {
                            NotificationDaySection(
                                modifier = Modifier.fillMaxWidth(),
                                notificationType = notificationType
                            ) {
                                notificationType = it
                                viewModelLens.onEvent(LensSettingEvent.NotificationDay(it))
                            }
                            NotificationTimeSection(
                                isDarkTheme = isDarkTheme,
                                modifier = Modifier.fillMaxWidth(),
                                notificationTimeHour = notificationTimeHour,
                                setNotificationTimeHour = {
                                    notificationTimeHour = it
                                    viewModelLens.onEvent(LensSettingEvent.NotificationTimeHour(it))
                                },
                                notificationTimeMinute = notificationTimeMinute,
                                setNotificationTimeMinute = {
                                    notificationTimeMinute = it
                                    viewModelLens.onEvent(LensSettingEvent.NotificationTimeMinute(it))
                                }
                            )
                        }
                    }
                    ToggleButtonSection(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.lens_power),
                        isUseNotification = null,
                        flag = isShowLensPowerSection
                    ) {
                        isShowLensPowerSection = !isShowLensPowerSection
                        viewModelLens.onEvent(
                            LensSettingEvent.IsShowLensPowerSection(isShowLensPowerSection)
                        )
                    }
                    SimpleDivider()
                    AnimatedVisibility(visible = isShowLensPowerSection) {
                        LensPowerSection(
                            isDarkTheme = isDarkTheme,
                            modifier = Modifier.fillMaxWidth(),
                            leftLensPower = leftLensPower.toDouble(),
                            setLeftLensPower = {
                                leftLensPower = it.toString()
                                viewModelLens.onEvent(LensSettingEvent.LeftPower(it.toString()))
                            },
                            rightLensPower = rightLensPower.toDouble(),
                            setRightLensPower = {
                                rightLensPower = it.toString()
                                viewModelLens.onEvent(LensSettingEvent.RightPower(it.toString()))
                            }
                        )
                    }
                }
                SaveSettingButton(modifier = Modifier.fillMaxWidth()) {
                    isShowLensPeriodPicker = false
                    Toast.makeText(
                        context,
                        context.getString(R.string.success_save_setting),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModelLens.onEvent(LensSettingEvent.SaveLensSetting)
                    navController.popBackStack()
                }
            }
        }
    )
}